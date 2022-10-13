package com.cognizant.collaterals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test - CollateralManagementMicroserviceApplication class
 */
@SpringBootTest
class CollateralsManagementMicroserviceApplicationTests {

	@Test
	void applicationStarts() throws IOException {
		CollateralsManagementMicroserviceApplication.main(new String[] {});
		assertTrue(true);
	}
}
