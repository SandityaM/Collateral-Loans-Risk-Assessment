package com.cognizant.collaterals.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

/**
 * This is the class for Global Exception Handling
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This handles all the response for CollateralNotFoundException
	 * 
	 * @param exception
	 * @return ResponseEntity<ApiErrorResponse>
	 */
	@ExceptionHandler(CollateralNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(CollateralNotFoundException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage(exception.getLocalizedMessage());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * This handles all the response for CollateralAlreadyExistsException
	 * 
	 * @param exception
	 * @return ResponseEntity<ApiErrorResponse>
	 */
	@ExceptionHandler(CollateralAlreadyExistsException.class)
	public ResponseEntity<ApiErrorResponse> handleCollateralAlreadyExistsException(
			CollateralAlreadyExistsException exception) {
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