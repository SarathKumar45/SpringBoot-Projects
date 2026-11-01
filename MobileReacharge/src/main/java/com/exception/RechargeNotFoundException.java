package com.exception;

public class RechargeNotFoundException extends RuntimeException {
	public RechargeNotFoundException(String message) {
		super(message);
	}
}