package com.cognizant.collaterals.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - RealEstate class
 */
@SpringBootTest
class RealEstateTests {

	@Test
	void testRealEstateBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RealEstate.class);
	}

}
