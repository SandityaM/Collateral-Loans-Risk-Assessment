package com.cognizant.collaterals.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - Collateral class
 */
@SpringBootTest
class CollateralTests {

	@Test
	void testCollaterallBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(Collateral.class);
	}

}
