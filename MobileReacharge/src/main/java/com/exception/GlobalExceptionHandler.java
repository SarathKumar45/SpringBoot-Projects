package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RechargeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handleRechargeNotFound(RechargeNotFoundException ex) {
		Map<String, Object> error = new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", 404);
		error.put("error", "Not Found");
		error.put("message", ex.getMessage());
		return error;
	}

	@ExceptionHandler(InvalidRechargeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleInvalidRecharge(InvalidRechargeException ex) {
		Map<String, Object> error = new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", 400);
		error.put("error", "Bad Request");
		error.put("message", ex.getMessage());
		return error;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, Object> error = new HashMap<>();
		Map<String, String> fieldErrors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

		error.put("timestamp", LocalDateTime.now());
		error.put("status", 400);
		error.put("error", "Validation Failed");
		error.put("errors", fieldErrors);
		return error;
	}
}