package com.cts.project.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

/**
 * Test - ApiErrorResponse class
 */
@SpringBootTest
public class ApiErrorResponseTest {

	private Throwable throwable;

	@Before
	public void init() {
		throwable = new Throwable("Hello");
	}

	@Test
	public void testingApiErrorResponse() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ApiErrorResponse.class);
	}

	@Test
	public void testApiErrorResponseThreeArgument() {
		ApiErrorResponse apiErr = new ApiErrorResponse(HttpStatus.OK, throwable);
		assertEquals(HttpStatus.OK, apiErr.getHttpStatus());
	}

	@Test
	public void testApiErrorResponseTwoArgument() {
		ApiErrorResponse apiErr = new ApiErrorResponse(HttpStatus.OK, "Hello", throwable);
		assertEquals("Hello", apiErr.getMessage());
	}

}
