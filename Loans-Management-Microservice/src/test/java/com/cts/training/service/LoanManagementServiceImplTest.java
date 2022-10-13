package com.cts.training.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import feign.FeignException;

import com.cts.training.exception.CollateralTypeNotFoundException;
import com.cts.training.exception.CustomerLoanNotFoundException;
import com.cts.training.exception.LoanNotFoundException;
import com.cts.training.feign.CollateralFeign;
import com.cts.training.model.CustomerLoan;
import com.cts.training.model.Loan;
import com.cts.training.pojo.CashDeposit;
import com.cts.training.pojo.CollateralType;
import com.cts.training.pojo.RealEstate;
import com.cts.training.repo.CustomerLoanRepo;

import com.cts.training.repo.LoanRepo;

/**
 * @Test LoanManagementServiceImpl Class
 * @author POD-6
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanManagementServiceImplTest {

	@Mock
	CustomerLoanRepo clRepo;
	@Mock
	LoanRepo lRepo;
	@Mock
	CollateralFeign cfeign;

	@InjectMocks
	LoanManagementServiceImpl loanService;

	private CustomerLoan customerLoan;
	private RealEstate realEstate;
	private CashDeposit cashDeposit;
	private Loan loan;

	@Before
	public void setUp() throws Exception {
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


		loan = new Loan();
		loan.setCollateralType("CASH_DEPOSIT");
		loan.setInterestRate(20.0);
		loan.setLoanProductId(11);
		loan.setLoanProductName("Home Loan");
		loan.setMaxLoanEligible(500000.0);
		loan.setTenure(24);

		customerLoan = new CustomerLoan();
		customerLoan.setCollateralId(1);
		customerLoan.setLoanId(1001);
		customerLoan.setCustomerId(1000001);
		customerLoan.setLoanPrincipal(500000.0);
		customerLoan.setInterest(15.0);
		customerLoan.setEmi(15000.0);
		customerLoan.setLoanProductId(11);
		customerLoan.setTenure(24);
		when(clRepo.findById(1001)).thenReturn(Optional.ofNullable(customerLoan));
	}

	@Test
	public void testGetLoanDetails() throws CustomerLoanNotFoundException {
		assertEquals(customerLoan, loanService.getLoanDetails(1001, 1000001));
	}

	@Test(expected = CustomerLoanNotFoundException.class)
	public void testGetLoanDetailsExceptionForFindById() throws CustomerLoanNotFoundException {
		loanService.getLoanDetails(1002, 1000001);
	}

	@Test(expected = CustomerLoanNotFoundException.class)
	public void testGetLoanDetailsExceptionForCustomerId() throws CustomerLoanNotFoundException {
		customerLoan.setCustomerId(100001);
		loanService.getLoanDetails(1001, 1000001);
	}

	@Test(expected = LoanNotFoundException.class)
	public void testSaveRealEstate() throws CustomerLoanNotFoundException, LoanNotFoundException {
		loanService.saveRealEstate("user1", realEstate);
	}

	@Test(expected = CustomerLoanNotFoundException.class)
	public void testSaveRealEstateException() throws CustomerLoanNotFoundException, LoanNotFoundException {
		realEstate.setLoanId(1002);
		loanService.saveRealEstate("user1", realEstate).getStatusCode();
	}

	@Test(expected = LoanNotFoundException.class)
	public void testSaveCashDeposit() throws CustomerLoanNotFoundException, LoanNotFoundException {
		loanService.saveCashDeposit("user1", cashDeposit);
	}

	@Test(expected = CustomerLoanNotFoundException.class)
	public void testSaveCashDepositException() throws CustomerLoanNotFoundException, LoanNotFoundException {
		cashDeposit.setLoanId(1002);
		loanService.saveCashDeposit("user1", cashDeposit);
	}

	@Test(expected = LoanNotFoundException.class)
	public void testWhenLoanIdDoesNotExistSaveCashDeposit() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		loanService.saveCashDeposit("user1", cashDeposit);
	}
	@Test
	public void testWhenLoanIdExistSaveCashDeposit() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(loan));
		when(cfeign.saveCashDepositCollateral("user1", cashDeposit)).thenReturn(new ResponseEntity<>("OK",HttpStatus.OK));
		assertEquals(200,loanService.saveCashDeposit("user1", cashDeposit).getStatusCodeValue());
	}
	@Test(expected = CollateralTypeNotFoundException.class)
	public void testWhenLoanIdExistSaveButCollateralTypeNotMatchedCashDeposit() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(loan));
		loan.setCollateralType("REAL_ESTATE");
		loanService.saveCashDeposit("user1", cashDeposit);
	}
	@Test(expected = CollateralTypeNotFoundException.class)
	public void testWhenLoanIdExistFeignExceptionSaveCashDeposit() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(loan));
		when(cfeign.saveCashDepositCollateral("user1", cashDeposit)).thenThrow(FeignException.class);
		loanService.saveCashDeposit("user1", cashDeposit);
	}

	@Test(expected = LoanNotFoundException.class)
	public void testWhenLoanIdDoesNotExistSaveRealEstate() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		loanService.saveRealEstate("user1", realEstate);
	}
	@Test
	public void testWhenLoanIdExistSaveRealEstate() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(loan));
		loan.setCollateralType("REAL_ESTATE");
		when(cfeign.saveRealEstateCollateral("user1", realEstate)).thenReturn(new ResponseEntity<>("OK",HttpStatus.OK));
		assertEquals(200,loanService.saveRealEstate("user1", realEstate).getStatusCodeValue());
	}
	@Test(expected = CollateralTypeNotFoundException.class)
	public void testWhenLoanIdExistSaveButCollateralTypeNotMatchedRealEstate() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(loan));
		loan.setCollateralType("CASH_DEPOSIT");
		loanService.saveRealEstate("user1", realEstate);
	}
	@Test(expected = CollateralTypeNotFoundException.class)
	public void testWhenLoanIdExistFeignExceptionSaveRealEstate() throws CustomerLoanNotFoundException, LoanNotFoundException {
		when(lRepo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(loan));
		loan.setCollateralType("REAL_ESTATE");
		when(cfeign.saveRealEstateCollateral("user1", realEstate)).thenThrow(FeignException.class);
		loanService.saveRealEstate("user1", realEstate);
	}

}
