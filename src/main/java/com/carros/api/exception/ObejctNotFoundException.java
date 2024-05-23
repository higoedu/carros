package com.carros.api.exception;

public class ObejctNotFoundException extends RuntimeException{
	public ObejctNotFoundException(String message) {
		super(message);
	}
	
	public ObejctNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
