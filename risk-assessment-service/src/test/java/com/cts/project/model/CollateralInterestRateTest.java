package com.cts.project.model;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - CollateralInterestRate class
 */
@SpringBootTest
public class CollateralInterestRateTest {

	@Test
	public void testModelCollateralInterestRate() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(CollateralInterestRate.class);
	}

}
