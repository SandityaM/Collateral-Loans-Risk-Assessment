package com.cognizant.collaterals.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test - GlobalExceptionHandler class
 */
@SpringBootTest
class GlobalExceptionHandlerTest {

	@Autowired
	GlobalExceptionHandler globalExceptionHandler;

	ApiErrorResponse apiErrorResponse;

	@BeforeEach
	void setUp() {
		apiErrorResponse = new ApiErrorResponse();
	}

	@Test
	void handlesCollateralAlreadyExistsExceptionTest() {
		CollateralAlreadyExistsException collateralAlreadyExistsException = new CollateralAlreadyExistsException(
				"Collateral Already Exist");
		globalExceptionHandler.handleCollateralAlreadyExistsException(collateralAlreadyExistsException);
		apiErrorResponse.setMessage(collateralAlreadyExistsException.getMessage());
		apiErrorResponse.setLocalizedMessage(collateralAlreadyExistsException.getLocalizedMessage());
		ResponseEntity<?> entity = new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCode().value());
	}

	@Test
	void handlesCollateralNotFoundExceptionTest() {
		CollateralNotFoundException collateralNotFoundException = new CollateralNotFoundException(
				"Collateral Type Not found");
		globalExceptionHandler.handleNotFoundException(collateralNotFoundException);
		apiErrorResponse.setMessage(collateralNotFoundException.getMessage());
		apiErrorResponse.setLocalizedMessage(collateralNotFoundException.getLocalizedMessage());
		ResponseEntity<?> entity = new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCode().value());
	}

}
