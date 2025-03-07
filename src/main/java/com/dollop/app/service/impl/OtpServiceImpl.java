package com.dollop.app.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.OtpStorage;
import com.dollop.app.entity.TempUser;
import com.dollop.app.entity.User;
import com.dollop.app.enums.OtpType;
import com.dollop.app.enums.TokenType;
import com.dollop.app.exception.OtpExpiredException;
import com.dollop.app.exception.OtpNotVerifiedException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.repo.ITempUserRepository;
import com.dollop.app.repo.IUserRepository;
import com.dollop.app.repo.OtpRepository;
import com.dollop.app.reponse.ApiResponse;
import com.dollop.app.service.IOtpService;
import com.dollop.app.utils.AppUtils;
import com.dollop.app.utils.JwtUtil;
@Service
public class OtpServiceImpl implements IOtpService {
	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private OtpRepository otpRepo;
	@Autowired
	private ITempUserRepository tUserRepo;
	@Autowired
	private JwtUtil util;
	@Autowired
	private AppUtils appUtils;

	public String sendOtp(String toEmail, OtpType otpType) {
		OtpStorage storeOtp = new OtpStorage();

		String otp = generateOtp();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Your OTP Code");
		message.setText("Your OTP is: " + otp + ". It will expire in 5 minutes.");

		mailSender.send(message);

		LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(2); // OTP valid for 2 minutes

		storeOtp.setEmail(toEmail);
		storeOtp.setOtp(otp);
		storeOtp.setExpiryTime(expiryTime);
		storeOtp.setType(otpType);
		otpRepo.save(storeOtp);

		return otp;
	}
	private String generateOtp() {
		return String.valueOf(new Random().nextInt(900000) + 100000); // 6-digit OTP
	}

	@Override
	public ResponseEntity<ApiResponse> verifyOtp(String otp) {
		String token = this.appUtils.getTokenFromHeader();
//		if(token!=null)
		String email = util.getUsername(token);

		OtpStorage dbOtp = otpRepo.findByEmail(email);
		if (dbOtp != null) {
			if (TokenType.AUTH_TOKEN.toString().equals((String) util.getHeader(token, "tokenType"))) {
				if (dbOtp.getExpiryTime().isAfter(LocalDateTime.now()) && dbOtp.getOtp().equals(otp)) {

					if ("REGISTER".toString().equals(util.getHeader(token, "otpType"))) {
						TempUser tu = tUserRepo.findById(email).get();
						User u = User.builder().email(email).name(tu.getName()).password(tu.getPassword()).role("User")
								.status(true).isDeleted(false).build();

						userRepo.save(u);
						tUserRepo.deleteById(email);
						dbOtp.setExpiryTime(LocalDateTime.now());
						otpRepo.save(dbOtp);
						String accessToken = util.generateToken(email, OtpType.REGISTER, TokenType.ACCESS_TOKEN);
						return new ResponseEntity<ApiResponse>(
								ApiResponse.builder().message("User successfully verify").response(accessToken).build(),
								HttpStatus.OK);
					} else if ("LOGIN".toString().equals(util.getHeader(token, "otpType"))) {
						dbOtp.setExpiryTime(LocalDateTime.now());
						otpRepo.save(dbOtp);
						String accessToken = util.generateToken(email, OtpType.LOGIN, TokenType.ACCESS_TOKEN);
						return new ResponseEntity<ApiResponse>(
								ApiResponse.builder().message("User successfully verify").response(accessToken).build(),
								HttpStatus.OK);
					}
					throw new OtpNotVerifiedException("Invalid OTP");
				}
				throw new OtpExpiredException("Otp expired");
			}
			throw new OtpNotVerifiedException("Invalid OTP");
		}
		throw new UserNotFoundException("User Not Found " + email);

	}

}
