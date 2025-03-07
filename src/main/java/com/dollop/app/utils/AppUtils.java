package com.dollop.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AppUtils {

	@Autowired
	private HttpServletRequest servletRequest;

	public String getTokenFromHeader() {
		String header = this.servletRequest.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer "))
			header = header.substring(7);

		return header;
	}
}
