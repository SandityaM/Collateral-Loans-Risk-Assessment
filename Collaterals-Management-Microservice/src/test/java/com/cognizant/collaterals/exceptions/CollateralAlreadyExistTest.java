package com.cognizant.collaterals.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - CollateralAlreadyExistsException class
 */
@SpringBootTest
class CollateralAlreadyExistTest {

	@Test
	void testCollateralAlreadyExist() {
		CollateralAlreadyExistsException collateralAlreadyExistsException = new CollateralAlreadyExistsException(
				"Collateral Already Exist");
		assertEquals("Collateral Already Exist", collateralAlreadyExistsException.getMessage());
	}
}
