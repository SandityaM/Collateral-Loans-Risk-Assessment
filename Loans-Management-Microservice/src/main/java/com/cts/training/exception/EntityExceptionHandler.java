package com.cts.training.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Thsi is EntityExceptionHandler used to handle 500 INTERNAL SERVER ERROR
 */
@ControllerAdvice
public class EntityExceptionHandler {

/**
 * This is for Returning an error into the formatted response
 * @param ex
 * @param request
 * @return
 */
@ExceptionHandler(value = { Exception.class })
public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
          ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
