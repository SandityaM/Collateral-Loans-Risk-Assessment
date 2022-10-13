package com.cts.training.collateralwebportal.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import feign.FeignException;
@ControllerAdvice
public class EntityExceptionHandler {
@ExceptionHandler(value = { Exception.class })
public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
          ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

@ExceptionHandler(FeignException.class)
public ResponseEntity<ApiErrorResponse> handleFeignStatusException(FeignException ex,
		HttpServletResponse response) {
	ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
	errorResponse.setLocalizedMessage(ex.getLocalizedMessage());
	errorResponse.setMessage(ex.getMessage());
	return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
}
}