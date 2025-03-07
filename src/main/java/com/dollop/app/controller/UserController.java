package com.dollop.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.entity.User;
import com.dollop.app.payload.LoginRequest;
import com.dollop.app.payload.OtpRequest;
import com.dollop.app.payload.UserRequest;
import com.dollop.app.reponse.ApiResponse;
import com.dollop.app.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	@Autowired
	IUserService userService;
	@Value("${app.secret}")
	String secret;

	@PostMapping("/insert")
	public ResponseEntity<ApiResponse> register(@Valid @RequestBody UserRequest user) {
		return new ResponseEntity<ApiResponse>(userService.register(user), HttpStatus.OK);
	}

	@GetMapping("/showUsers")
	public ResponseEntity<List<User>> showUser() {
		return new ResponseEntity<List<User>>(userService.showUser(), HttpStatus.OK);
	}

	@GetMapping("/changeStatus")
	public ApiResponse changeStatus(
			@NotEmpty(message = "Id cannot be empty. Please provide a Id.") @RequestParam String id) {
		return ApiResponse.builder().message("status change Successfully")
				.response(new ResponseEntity<>(userService.changeStatus(id), HttpStatus.OK)).build();
	}

	@GetMapping("/verifyOtp")
	public ResponseEntity<ApiResponse> verifyOtp(@Valid @RequestBody OtpRequest otp) {
		System.out.println(otp);
		return userService.verifyOtp(otp);
	}

//	@GetMapping("/changeStatus")
//	public ApiResponse changeStatus(@RequestParam String id) {
//		return ApiResponse.builder().message("status change Successfully")
//				.response(new ResponseEntity<>(userService.changeStatus(id), HttpStatus.OK)).build();
//	}

	@GetMapping("/login")
	public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest user) {
		
		String userEmail=user.getEmail();
		String password=user.getPassword();
		return new ResponseEntity<ApiResponse>(userService.login(userEmail, password), HttpStatus.OK);
	}

}
