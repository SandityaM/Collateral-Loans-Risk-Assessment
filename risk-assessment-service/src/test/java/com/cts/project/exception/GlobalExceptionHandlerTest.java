package com.cts.project.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - GlobalExceptionHandler class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

	@Autowired
	GlobalExceptionHandler globalExceptionHandler;

	ApiErrorResponse apiErrorResponse;

	@Before
	public void setUp() {
		apiErrorResponse = new ApiErrorResponse();
	}

	@Test
	public void handlesLoanNotFoundExceptionTest() {
		LoanNotFoundException loanNotFoundException = new LoanNotFoundException("Loan not found Exception");
		globalExceptionHandler.handleLoanNotFoundException(loanNotFoundException);
		apiErrorResponse.setMessage(loanNotFoundException.getMessage());
		apiErrorResponse.setLocalizedMessage(loanNotFoundException.getLocalizedMessage());
		ResponseEntity<?> entity = new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCode().value());
	}

}
