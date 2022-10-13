package com.cts.project.model;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * CollateralRiskTest
 */
@SpringBootTest
public class CollateralRiskTest {

	@Test
	public void testModelCollateralRisk() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(CollateralRisk.class);
	}

}