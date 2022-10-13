package com.cognizant.collaterals.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - CashDeposit class
 */
@SpringBootTest
class CashDepositTests {

	@Test
	void testCashDepositBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(CashDeposit.class);
	}

}
