package com.dollop.app.exception;

public class OtpExpiredException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OtpExpiredException() {
		super();
	}

	public OtpExpiredException(String message) {
		super(message);
	}
	

}
