package com.cognizant.collaterals.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.collaterals.exceptions.CollateralAlreadyExistsException;
import com.cognizant.collaterals.exceptions.CollateralNotFoundException;
import com.cognizant.collaterals.model.CashDeposit;
import com.cognizant.collaterals.model.Collateral;
import com.cognizant.collaterals.model.CollateralType;
import com.cognizant.collaterals.model.RealEstate;
import com.cognizant.collaterals.model.ReturnToRisk;
import com.cognizant.collaterals.repository.CashDepositRepository;
import com.cognizant.collaterals.repository.CollateralRepository;
import com.cognizant.collaterals.repository.RealEstateRepository;

/**
 * Test - CollateralServiceImpl class
 */
@SpringBootTest
class CollateralServiceImplTests {

	@Mock
	private CollateralRepository cRepo;
	@Mock
	private CashDepositRepository cashRepo;
	@Mock
	private RealEstateRepository realRepo;

	@InjectMocks
	private CollateralServiceImpl service;

	RealEstate realEstate;
	CashDeposit cashDeposit;
	ReturnToRisk returnToRisk;
	Collateral collateral;

	@BeforeEach
	void init() {
		collateral = new Collateral();
		collateral.setCollateralId(101);
		collateral.setCollateralType(CollateralType.REAL_ESTATE);
		collateral.setCurrentValue(150000.0);
		collateral.setLoanId(1001);
		collateral.setOwnerAddress("xyz");
		collateral.setOwnerContact(72564545L);
		collateral.setOwnerName("uvw");

		realEstate = new RealEstate();
		realEstate.setCollateralId(101);
		realEstate.setCollateralType(CollateralType.REAL_ESTATE);
		realEstate.setCurrentValue(1500000.0);
		realEstate.setDepreciationRate(20.0);
		realEstate.setLoanId(1001);
		realEstate.setOwnerAddress("Kolkata");
		realEstate.setOwnerContact(789456123L);
		realEstate.setOwnerName("Himashu");
		realEstate.setRatePerSqFt(500.0);

		cashDeposit = new CashDeposit();
		cashDeposit.setCollateralId(102);
		cashDeposit.setCollateralType(CollateralType.CASH_DEPOSIT);
		cashDeposit.setCurrentValue(1000000.0);
		cashDeposit.setDepositAmount(800000.0);
		cashDeposit.setInterestRate(15.0);
		cashDeposit.setLoanId(1001);
		cashDeposit.setLockPeriod(10.0);
		cashDeposit.setOwnerAddress("Kolkata");
		cashDeposit.setOwnerContact(789456123L);
		cashDeposit.setOwnerName("Himashu");

		returnToRisk = new ReturnToRisk();
		returnToRisk.setCollateralType(CollateralType.REAL_ESTATE);
		returnToRisk.setInterestRate(15.0);
		returnToRisk.setRatePerSqFt(500.0);
	}

	@Test
	void testGetCollateralsByValidLoanAndCustomer() {
		when(cRepo.findById(1001)).thenReturn(Optional.ofNullable(collateral));
		assertEquals(collateral.toString(), service.getCollaterals(1001, 100001).toString());
	}

	@Test
	void testGetCollateralsByInvalidLoanAndCustomer() throws CollateralNotFoundException {
		when(cRepo.findById(1002)).thenThrow(CollateralNotFoundException.class);
		assertThrows(CollateralNotFoundException.class, () -> {
			service.getCollaterals(1002, 10001);
		});
	}

	@Test
	void testSaveRealStateCollateral() {
		when(cRepo.existsById(1001)).thenReturn(false);
		when(realRepo.save(realEstate)).thenReturn(realEstate);
		assertTrue(service.saveRealEstateCollateral(realEstate));
	}

	@Test
	void testSaveRealStateCollateralCollateralExist() {
		when(cRepo.existsById(1001)).thenReturn(true);
		assertThrows(CollateralAlreadyExistsException.class, () -> {
			service.saveRealEstateCollateral(realEstate);
		});

	}

	@Test
	void testSaveCashDeposite() {
		when(cRepo.existsById(cashDeposit.getLoanId())).thenReturn(false);
		when(cashRepo.save(cashDeposit)).thenReturn(cashDeposit);
		assertTrue(service.saveCashDepositCollateral(cashDeposit));
	}

	@Test
	void testSaveCashCollateralCollateralExist() {
		when(cRepo.existsById(cashDeposit.getLoanId())).thenReturn(true);
		assertThrows(CollateralAlreadyExistsException.class, () -> {
			service.saveCashDepositCollateral(cashDeposit);
		});
	}
}