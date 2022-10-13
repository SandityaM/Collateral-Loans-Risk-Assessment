package com.cognizant.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - UserNameNumericException class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserNameNumericExceptionTest {

	@Mock
	UserNameNumericException userNameNumericException;

	@Test
	public void testOneArgConstructor() {
		UserNameNumericException userNameNumericException = new UserNameNumericException("User name is numeric");
		assertEquals("User name is numeric", userNameNumericException.getMessage());
	}
}
