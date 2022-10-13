package com.cts.training.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Test CustomerLoanNotFoundException Class
 * @author POD-6
 */
@SpringBootTest
public class CustomerLoanNotFoundExceptionTest {

	CustomerLoanNotFoundException customerNotFound;

	@Before
	public void init() {
		customerNotFound = new CustomerLoanNotFoundException("Customer Loan Not Found Exception");
	}

	@Test
	public void testLoanNotFound() {
		assertEquals("Customer Loan Not Found Exception", customerNotFound.getMessage());
	}

}
