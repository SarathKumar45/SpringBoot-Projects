package com.exception;

public class InvalidRechargeException extends RuntimeException {
	public InvalidRechargeException(String message) {
		super(message);
	}
}