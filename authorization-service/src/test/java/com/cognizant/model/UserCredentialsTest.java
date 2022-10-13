package com.cognizant.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - UserCredentials class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserCredentialsTest {

	@Mock
	UserCredentials user;

	@Before
	public void setUp() {
		user = new UserCredentials();
		user.setUserName("vishal");
		user.setPassword("abc");
	}

	@Test
	public void userCredentialsAllConstructorTest() {
		UserCredentials userCredentials = new UserCredentials("vishal", "abc");
		assertEquals("vishal", userCredentials.getUserName());
	}

	@Test
	public void userNameTest() {
		assertEquals("vishal", user.getUserName());
	}

	@Test
	public void passwordTest() {
		assertEquals("abc", user.getPassword());
	}

	@Test
	public void toStringTest() {
		String string = user.toString();
		assertEquals(string, user.toString());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = user.equals(user);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = user.hashCode();
		assertEquals(hashCode, user.hashCode());
	}

}
