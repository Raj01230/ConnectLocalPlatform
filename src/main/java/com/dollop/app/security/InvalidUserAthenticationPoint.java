package com.dollop.app.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.dollop.app.exception.ExpiredTokenException;
import com.dollop.app.reponse.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InvalidUserAthenticationPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		// Retrieve stored exception from the request attribute
		Exception exception = (Exception) request.getAttribute("exception");

		int status = HttpServletResponse.SC_UNAUTHORIZED;
	
		String message = "Token Expired!";

		if (exception instanceof ExpiredJwtException) {
			message = "Token has expired. Please log in again.";
		} else if (exception instanceof SignatureException) {
			message = "Invalid token signature.";
		} else if (exception instanceof MalformedJwtException) {
			message = "Malformed JWT token.";
		} else if (exception instanceof UnsupportedJwtException) {
			message = "Unsupported JWT token.";
		} else if (exception instanceof ExpiredTokenException) {
			message = exception.getMessage();
		}

		// Convert map to JSON
		response.setContentType("application/json");
		response.setStatus(status);
		response.getWriter()
				.write(new ObjectMapper().writeValueAsString(ErrorResponse.builder().message(message).build()));
		response.getWriter().flush();
	}
}
