package com.dollop.app.service;

import org.springframework.http.ResponseEntity;

import com.dollop.app.enums.OtpType;
import com.dollop.app.reponse.ApiResponse;

public interface IOtpService {
	public ResponseEntity<ApiResponse> verifyOtp(String otp) ;
	public String sendOtp(String toEmail, OtpType otpType);
}
