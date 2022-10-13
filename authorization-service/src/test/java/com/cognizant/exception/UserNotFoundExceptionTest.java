package com.cognizant.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - UserNotFoundException class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserNotFoundExceptionTest {

	@Mock
	UserNotFoundException userNotFoundException;

	@Test
	public void testOneArgConstructor() {
		UserNotFoundException userNotFoundException = new UserNotFoundException("User not found");
		assertEquals("User not found", userNotFoundException.getMessage());
	}

}
