package com.cognizant.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler class
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles user name null exception
	 * 
	 * @param ex
	 * @return This returns the custom error message and the time stamp and the HTTP
	 *         status code
	 */
	@ExceptionHandler(UserNameNumericException.class)
	public ResponseEntity<CustomErrorResponse> handlesUserNameNumericException(
			UserNameNumericException userNameNumericException) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(userNameNumericException.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles user name numeric exception
	 * 
	 * @param ex
	 * @return This returns the custom error message and the time stamp and the HTTP
	 *         status code
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handlesUserNotFoundException(
			UserNotFoundException userNotFoundException) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(userNotFoundException.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
