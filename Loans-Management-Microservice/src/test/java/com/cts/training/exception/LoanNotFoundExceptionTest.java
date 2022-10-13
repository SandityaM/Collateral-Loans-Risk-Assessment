package com.cts.training.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Test GlobalExceptionHandler Class
 * @author POD-6
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
