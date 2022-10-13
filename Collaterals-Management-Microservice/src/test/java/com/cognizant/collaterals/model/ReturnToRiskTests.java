package com.cognizant.collaterals.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - ReturnToRisk class
 */
@SpringBootTest
class ReturnToRiskTests {

	@Test
	void testRealEstateBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RealEstate.class);
	}

}
