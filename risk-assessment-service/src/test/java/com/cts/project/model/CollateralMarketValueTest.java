package com.cts.project.model;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - CollateralMarketValue class
 */
@SpringBootTest
public class CollateralMarketValueTest {

	@Test
	public void testModelCollateralMarketValue() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(CollateralMarketValue.class);
	}

}
