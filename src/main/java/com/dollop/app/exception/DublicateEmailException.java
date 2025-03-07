package com.dollop.app.exception;

public class DublicateEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DublicateEmailException() {
		super();
	}

	public DublicateEmailException(String message) {
		super(message);
	}

	
}
