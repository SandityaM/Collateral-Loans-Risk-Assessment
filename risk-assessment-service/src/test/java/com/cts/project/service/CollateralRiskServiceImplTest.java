package com.cts.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import feign.FeignException;

import com.cts.project.exception.LoanNotFoundException;
import com.cts.project.feign.CollateralFeign;
import com.cts.project.model.CollateralInterestRate;
import com.cts.project.model.CollateralMarketValue;
import com.cts.project.pojo.CollateralRiskPercent;
import com.cts.project.pojo.CollateralType;
import com.cts.project.pojo.ReturnToRisk;
import com.cts.project.repository.CollateralInterestRateRepo;
import com.cts.project.repository.CollateralMarketValueRepo;

/**
 * Test - CollateralRiskServiceImpl class
 */
@RunWith(MockitoJUnitRunner.class)
public class CollateralRiskServiceImplTest {

	@Mock
	CollateralFeign collateralFeign;

	@Mock
	CollateralInterestRateRepo collateralInterestRateRepo;

	@Mock
	CollateralMarketValueRepo collateralMarketValueRepo;

	@InjectMocks
	CollateralRiskServiceImpl collateralRiskServiceImpl;

	private CollateralRiskPercent collateralRiskPer;
	private ReturnToRisk returnToRisk;
	private CollateralMarketValue collateralMarketValue;
	private CollateralInterestRate collateralInterestRate;

	@Before
	public void init() {

		collateralRiskPer = new CollateralRiskPercent();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		collateralRiskPer.setAssessedDate(formatter.format(date));
		collateralRiskPer.setRiskPercent(20.0);

		returnToRisk = new ReturnToRisk();
		returnToRisk.setCollateralType(CollateralType.REAL_ESTATE);
		returnToRisk.setInterestRate(12.0);
		returnToRisk.setRatePerSqFt(200.0);

		collateralMarketValue = new CollateralMarketValue();
		collateralMarketValue.setRateId(1);
		collateralMarketValue.setRatePerSqrFeet(250.0);

		collateralInterestRate = new CollateralInterestRate();
		collateralInterestRate.setInterestId(1);
		collateralInterestRate.setInterestRate(15.0);

		when(collateralMarketValueRepo.findById(1)).thenReturn(Optional.ofNullable(collateralMarketValue));
		when(collateralInterestRateRepo.findById(1)).thenReturn(Optional.ofNullable(collateralInterestRate));
		when(collateralFeign.getCollateralWithLoanId("user1", 1001)).thenReturn(returnToRisk);
	}

	@Test
	public void testGetRiskAssessmentWhenReturnSomeRiskPercentForRealEstate() throws LoanNotFoundException {
		returnToRisk.setCollateralType(CollateralType.REAL_ESTATE);
		assertEquals(collateralRiskPer.toString(),
				collateralRiskServiceImpl.getRiskAssessment("user1", 1001).toString());
	}

	@Test
	public void testGetRiskAssessmentWhenReturn0RiskPercentForRealEstate() throws LoanNotFoundException {
		returnToRisk.setCollateralType(CollateralType.REAL_ESTATE);
		collateralMarketValue.setRatePerSqrFeet(200.0);
		collateralRiskPer.setRiskPercent(0.0);
		assertEquals(collateralRiskPer.toString(),
				collateralRiskServiceImpl.getRiskAssessment("user1", 1001).toString());
	}

	@Test
	public void testGetRiskAssessmentWhenReturnSomeRiskPercentForCashDeposit() throws LoanNotFoundException {
		returnToRisk.setCollateralType(CollateralType.CASH_DEPOSIT);
		assertEquals(collateralRiskPer.toString(),
				collateralRiskServiceImpl.getRiskAssessment("user1", 1001).toString());
	}

	@Test
	public void testGetRiskAssessmentWhenReturn0RiskPercentForCashDeposit() throws LoanNotFoundException {
		returnToRisk.setCollateralType(CollateralType.CASH_DEPOSIT);
		collateralInterestRate.setInterestRate(12.0);
		collateralRiskPer.setRiskPercent(0.0);
		assertEquals(collateralRiskPer.toString(),
				collateralRiskServiceImpl.getRiskAssessment("user1", 1001).toString());
	}
	@Test(expected = LoanNotFoundException.class)
    public void testFeignExceptionForGetRiskASsesment() throws LoanNotFoundException {
        when(collateralFeign.getCollateralWithLoanId("user1", 1001)).
													thenThrow(FeignException.class);
        collateralRiskServiceImpl.getRiskAssessment("user1", 1001);
    }

}
