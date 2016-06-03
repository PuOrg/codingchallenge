package com.aconex.codingchallenge.exception;

public class CHException extends Exception {

	private static final long serialVersionUID = 1L;

	public CHException() {
		super();
	}

	public CHException(String message) {
		super(message);
	}

	public CHException(String message, Throwable cause) {
		super(message, cause);
	}

	public CHException(Throwable cause) {
		super(cause);
	}

}
