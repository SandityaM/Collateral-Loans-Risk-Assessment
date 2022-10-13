package com.cognizant.collaterals.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

/**
 * Test - ApiErrorResponse class
 */
@SpringBootTest
class ApiErrorResponseTest {

	private Throwable throwable;

	@BeforeEach
	void init() {
		throwable = new Throwable("Hello");
	}

	@Test
	void testingApiErrorResponse() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ApiErrorResponse.class);
	}

	@Test
	void testApiErrorResponseThreeArgument() {
		ApiErrorResponse apiErr = new ApiErrorResponse(HttpStatus.OK, throwable);
		assertEquals(HttpStatus.OK, apiErr.getHttpStatus());
	}

	@Test
	void testApiErrorResponseTwoArgument() {
		ApiErrorResponse apiErr = new ApiErrorResponse(HttpStatus.OK, "Hello", throwable);
		assertEquals("Hello", apiErr.getMessage());
	}

}
