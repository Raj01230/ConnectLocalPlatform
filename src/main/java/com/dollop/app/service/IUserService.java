package com.dollop.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.dollop.app.entity.User;
import com.dollop.app.payload.OtpRequest;
import com.dollop.app.payload.UserRequest;
import com.dollop.app.reponse.ApiResponse;

import jakarta.validation.Valid;

public interface IUserService {
	ApiResponse login(String email,String password);
	ApiResponse register(UserRequest user);
	List<User> showUser();
	Boolean changeStatus(String id);
	public ResponseEntity<ApiResponse> verifyOtp(OtpRequest otp) ;
	
}
