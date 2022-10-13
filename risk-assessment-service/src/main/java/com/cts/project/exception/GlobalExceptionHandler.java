package com.cts.project.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

/**
 * This is the class for Global Exception Handling
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * This handles all the response for LoanNotFoundException
	 * 
	 * @param exception
	 * @return ResponseEntity<ApiErrorResponse>
	 */
	@ExceptionHandler(LoanNotFoundException.class)
	protected ResponseEntity<ApiErrorResponse> handleLoanNotFoundException(LoanNotFoundException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage(exception.getLocalizedMessage());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * This handles all the response for FeignStatusException
	 * 
	 * @param exception
	 * @return ResponseEntity<ApiErrorResponse>
	 */
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ApiErrorResponse> handleFeignStatusException(FeignException ex,
			HttpServletResponse response) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
		errorResponse.setLocalizedMessage(ex.getLocalizedMessage());
		errorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
