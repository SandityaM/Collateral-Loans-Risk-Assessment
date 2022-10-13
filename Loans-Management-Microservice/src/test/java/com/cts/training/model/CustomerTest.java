package com.cts.training.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @Test Customer Class
 * @author POD-6
 */
public class CustomerTest {

	private Customer customer;

	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		customer.setCustomerId(101);
		customer.setCustomerEmailId("vishal@gmail.com");
		customer.setCustomerName("Vishal");
		customer.setCustomerPhoneNo("7365984621");
		customer.setCustomerAddress("Kolkata");
	}

	@Test
	public void testGetter() {
		assertEquals((Integer)101, customer.getCustomerId());
		assertEquals("Vishal", customer.getCustomerName());
		assertEquals("7365984621", customer.getCustomerPhoneNo());
		assertEquals("vishal@gmail.com", customer.getCustomerEmailId());
		assertEquals("Kolkata", customer.getCustomerAddress());
	}

	@Test
	public void testSetter() {
		customer.setCustomerEmailId("bharat@gmail.com");
		assertEquals("bharat@gmail.com", customer.getCustomerEmailId());
		assertEquals("Kolkata", customer.getCustomerAddress());
	}

}
