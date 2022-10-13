package com.cts.training.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.training.exception.CustomerLoanNotFoundException;
import com.cts.training.feign.AuthorisationClient;
import com.cts.training.model.CustomerLoan;
import com.cts.training.pojo.CashDeposit;
import com.cts.training.pojo.CollateralType;
import com.cts.training.pojo.RealEstate;
import com.cts.training.service.LoanManagementServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Test LoanManagementController
 * @author POD-6
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoanManagementController.class)
public class LoanManagementControllerTest {

	@MockBean
	LoanManagementServiceImpl loanService;

	@MockBean
	AuthorisationClient authClient;

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	private CustomerLoan customerLoan;
	private RealEstate realEstate;
	private CashDeposit cashDeposit;

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

		customerLoan = new CustomerLoan((Integer) 1001, (Integer) 10002, (Integer) 1000001, (Double) 1500000.0,
				(Integer) 36, (Double) 15.0, (Double) 15000.0, (Integer) 101);
		when(loanService.getLoanDetails((Integer) 1001, (Integer) 1000001)).thenReturn(customerLoan);
		when(loanService.getLoanDetails(1002, 1000001)).thenThrow(CustomerLoanNotFoundException.class);
		when(authClient.validate("user1")).thenReturn(true);
		when(loanService.saveRealEstate("user1", realEstate)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		when(loanService.saveCashDeposit("user1", cashDeposit)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
	}

	@Test
	public void testHealthCheck() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/loan-management/health-check").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200,result.getResponse().getStatus());
	}

	@Test
	public void testGetLoanDetails() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/loan-management/getLoanDetails/1001/1000001/")
				.header("Authorization", "user1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		CustomerLoan rCustomerLoan = mapper.readValue(result.getResponse().getContentAsString(), CustomerLoan.class);
		assertEquals(customerLoan.toString(), rCustomerLoan.toString());
	}

	@Test
	public void testGetLoanDetailsCustomerLoanNotFoundException() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/loan-management/getLoanDetails/1002/1000001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}

	@Test
	public void testGetLoanDetailsAuthorization() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/loan-management/getLoanDetails/1001/1000001/")
				.header("Authorization", "user2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("Customer Loan Found With LoanId:1001 But Not Found With CustomerId:1000001", result.getResponse().getContentAsString());
	}

	@Test
	public void testRealEstateCollateral() throws Exception {
		String json = mapper.writeValueAsString(realEstate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/loan-management/saveRealEstateCollateral")
				.header("Authorization", "user1").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Result: " + result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testRealEstateCollateralAuthorizationExeption() throws Exception {
		String json = mapper.writeValueAsString(realEstate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/loan-management/saveRealEstateCollateral")
				.header("Authorization", "user2").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Result: " + result.getResponse().getContentAsString());
		assertEquals(403, result.getResponse().getStatus());
	}

	@Test
	public void testCashDepositCollateral() throws Exception {
		String json = mapper.writeValueAsString(cashDeposit);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/loan-management/saveCashDepositCollateral")
				.header("Authorization", "user1").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testCashDepositeCollateralAuthorizationExeption() throws Exception {
		String json = mapper.writeValueAsString(cashDeposit);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/loan-management/saveCashDepositCollateral")
				.header("Authorization", "user2").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(403, result.getResponse().getStatus());
	}
}