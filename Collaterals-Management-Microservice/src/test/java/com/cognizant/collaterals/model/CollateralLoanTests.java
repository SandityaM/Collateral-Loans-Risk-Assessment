package com.cognizant.collaterals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - Collateral class
 */
@SpringBootTest
class CollateralLoanTests {

	private CollateralLoan collateralLoan;

	@Test
	void test_CollateralLoanDetails() throws Exception {
		collateralLoan = new CollateralLoan();
		collateralLoan.setColLoanId(1);
		collateralLoan.setLoanId(1);
		collateralLoan.setCollateralId(1);
		collateralLoan.setCollateralValues(100.5);
		collateralLoan.setPledgedDate(LocalDate.now());
		assertEquals(1, collateralLoan.getLoanId());
		assertEquals((Integer) 1, collateralLoan.getCollateralId());
		assertEquals(1, collateralLoan.getColLoanId());
		assertEquals(100.5, collateralLoan.getCollateralValues(), 0.1);
		assertEquals(LocalDate.now(), collateralLoan.getPledgedDate());
	}
}
