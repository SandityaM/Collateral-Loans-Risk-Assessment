package com.cts.project.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.WebRequest;

/**
 * Test - EntityException class
 */
@SpringBootTest
public class EntityExceptionHandlerTest {

	@Test
	public void testEntityException() {
		WebRequest web = null;
		LoanNotFoundException loan = new LoanNotFoundException("Loan Not Found");
		EntityExceptionHandler exp = new EntityExceptionHandler();
		assertEquals(500, exp.handleAnyException(loan, web).getStatusCodeValue());
	}

}
