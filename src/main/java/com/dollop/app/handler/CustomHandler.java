package com.dollop.app.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dollop.app.exception.DateFormateException;
import com.dollop.app.exception.DublicateEmailException;
import com.dollop.app.exception.EmptyOrNullFoundlException;
import com.dollop.app.exception.ExpiredTokenException;
import com.dollop.app.exception.InvalidPasswordException;
//import com.dollop.app.exception.FileSizeLimitExceededException;
//import com.dollop.app.exception.ImageNotFoundException;
//import com.dollop.app.exception.InvalidDateRangeException;
//import com.dollop.app.exception.InvalidFileFormateException;
import com.dollop.app.exception.InvalidStatusException;
import com.dollop.app.exception.OtpExpiredException;
import com.dollop.app.exception.OtpNotVerifiedException;
import com.dollop.app.exception.UserAlreadyExistsException;
import com.dollop.app.exception.UserNotFoundException;
//import com.dollop.app.exception.ProjectNotFoundException;
import com.dollop.app.reponse.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice

public class CustomHandler {
//	@ExceptionHandler(DepartmentNotFoundException.class)
//	public ErrorResponse name(DepartmentNotFoundException ex) {
//		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
//		
//	}
//	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ErrorResponse name(UserAlreadyExistsException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ErrorResponse> name(InvalidPasswordException ex) {
		return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().message(ex.getMessage()).response(HttpStatus.NOT_FOUND).build(),HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(DateFormateException.class)
	public ErrorResponse name(DateFormateException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DublicateEmailException.class)
	public ErrorResponse name(DublicateEmailException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorResponse name(ConstraintViolationException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(OtpExpiredException.class)
	public ErrorResponse name(OtpExpiredException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(OtpNotVerifiedException.class)
	public ErrorResponse name(OtpNotVerifiedException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ExpiredTokenException.class)
	public ResponseEntity<ErrorResponse> name(ExpiredTokenException ex) {
		System.err.println("EX :: "+ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED));

	}

	@ExceptionHandler(InvalidStatusException.class)
	public ErrorResponse name(InvalidStatusException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public ErrorResponse name(UserNotFoundException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(EmptyOrNullFoundlException.class)
	public ErrorResponse name(EmptyOrNullFoundlException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodNotArgsNotValidException(
			MethodArgumentNotValidException ex) {
		Map<String, String> respo = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			respo.put(fieldName, message);
		});

		return new ResponseEntity<>(respo, HttpStatus.BAD_REQUEST);
	}
}
