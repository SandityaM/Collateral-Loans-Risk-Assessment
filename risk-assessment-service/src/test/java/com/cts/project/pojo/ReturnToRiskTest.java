package com.cts.project.pojo;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - ReturnToRisk class
 */
@SpringBootTest
public class ReturnToRiskTest {

	@Test
	public void testPojoReturnToRisk() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ReturnToRisk.class);
	}
}
