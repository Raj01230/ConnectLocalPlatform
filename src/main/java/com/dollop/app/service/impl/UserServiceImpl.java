package com.dollop.app.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dollop.app.entity.TempUser;
import com.dollop.app.entity.User;
import com.dollop.app.enums.OtpType;
import com.dollop.app.enums.TokenType;
import com.dollop.app.exception.InvalidPasswordException;
import com.dollop.app.exception.UserAlreadyExistsException;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.payload.OtpRequest;
import com.dollop.app.payload.UserRequest;
import com.dollop.app.repo.ITempUserRepository;
import com.dollop.app.repo.IUserRepository;
import com.dollop.app.reponse.ApiResponse;
import com.dollop.app.service.IOtpService;
import com.dollop.app.service.IUserService;
import com.dollop.app.utils.JwtUtil;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private IOtpService otpService;

	@Autowired
	private ITempUserRepository tUserRepo;
	@Autowired
	private JwtUtil util;

	public UserServiceImpl() {
		super();
	}

	@Override
	public ApiResponse register(UserRequest user) {
		String email = user.getEmail();
		if (!userRepo.existsByEmail(email)) {

			TempUser u = TempUser.builder().email(user.getEmail()).name(user.getName()).password(user.getPassword())
					.build();
			System.out.println(u);
			tUserRepo.save(u);
			Map<String, Object> m = new HashMap<>();
			String token = util.generateToken(email, OtpType.REGISTER, TokenType.AUTH_TOKEN);
			m.put("token", token);
			m.put("otp", otpService.sendOtp(email, OtpType.REGISTER));

			return ApiResponse.builder().message("User successfully login")
					.response(m).build();
		}
		throw new UserAlreadyExistsException("User Already Exists");
	}

	@Override
	public ApiResponse login(String email, String password) {

		if (userRepo.existsByEmail(email)) {
			if (userRepo.existsByEmailAndPassword(email, password)) {
				Map<String, Object> m = new HashMap<>();
				String token = util.generateToken(email, OtpType.LOGIN, TokenType.AUTH_TOKEN);
				m.put("token", token);
				m.put("otp", otpService.sendOtp(email, OtpType.LOGIN));

				return ApiResponse.builder().message("Mobile Number and Password Verified Successfully, Please Enter Otp to Login")
						.response(m).build();
			} else
				throw new InvalidPasswordException("invalid Password");
		} else
			throw new UserNotFoundException("User Not Found " + email);

	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);

		// Assuming user.getRole() returns a single string, like "ROLE_USER"
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));

		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
	}

	@Override
	public List<User> showUser() {
		return userRepo.findByRole("User");
	}

	@Override
	public Boolean changeStatus(String id) {
		User u = userRepo.findById(id).get();
		if (userRepo.existsById(id)) {
			if (u.getStatus() == true) {
				u.setStatus(false);
			} else
				u.setStatus(true);
			userRepo.save(u);
			return true;
		} else
			throw new UserNotFoundException("User Not Found");
	}
	public ResponseEntity<ApiResponse> verifyOtp( OtpRequest otp) {
		System.out.println(otp);String otp1=otp.getOtp();
		return otpService.verifyOtp(otp1);
	}
}