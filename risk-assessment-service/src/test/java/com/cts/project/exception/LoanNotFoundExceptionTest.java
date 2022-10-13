package com.cts.project.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - LoanNotFoundException class
 */
@SpringBootTest
public class LoanNotFoundExceptionTest {

	LoanNotFoundException loanNotFound;

	@Before
	public void init() {
		loanNotFound = new LoanNotFoundException("Loan Not Found Exception");
	}

	@Test
	public void testLoanNotFound() {
		assertEquals("Loan Not Found Exception", loanNotFound.getMessage());
	}

}
