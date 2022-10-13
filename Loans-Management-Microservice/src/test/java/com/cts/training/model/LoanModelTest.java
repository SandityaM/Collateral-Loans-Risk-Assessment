package com.cts.training.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @Test Loan Class
 * @author POD-6
 */
public class LoanModelTest {

	private Loan loan;

	@Before
	public void setUp() throws Exception {
		loan = new Loan();
		loan.setLoanProductId((Integer) 1);
		loan.setLoanProductName("Educational");
		loan.setMaxLoanEligible((Double) 1500000.0);
		loan.setInterestRate(12.0);
		loan.setTenure(36);
		loan.setCollateralType("RealEstate");
	}

	@Test
	public void testGetter() {
		assertEquals("Educational", loan.getLoanProductName());
		assertEquals((Integer) 1, loan.getLoanProductId());
		assertEquals(1500000.0, loan.getMaxLoanEligible(), 0.1);
		assertEquals(12.0, loan.getInterestRate(), 0.1);
		assertEquals((Integer) 36, loan.getTenure());
		assertEquals("RealEstate", loan.getCollateralType());
	}

	@Test
	public void testSetter() {
		loan.setLoanProductName("Medical Loan");
		assertEquals("Medical Loan", loan.getLoanProductName());
	}

}
