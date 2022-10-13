package com.cognizant.collaterals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.collaterals.exceptions.CollateralNotFoundException;
import com.cognizant.collaterals.feign.AuthorisationClient;
import com.cognizant.collaterals.model.CashDeposit;
import com.cognizant.collaterals.model.Collateral;
import com.cognizant.collaterals.model.CollateralType;
import com.cognizant.collaterals.model.RealEstate;
import com.cognizant.collaterals.model.ReturnToRisk;
import com.cognizant.collaterals.service.CollateralService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test - CollateralController class
 */
@WebMvcTest(CollateralController.class)
class CollateralControllerTests {

	@MockBean
	CollateralService service;

	ObjectMapper mapper = new ObjectMapper();

	CollateralController controller;

	@MockBean
	AuthorisationClient authclient;

	@Autowired
	private MockMvc mockMvc;

	Collateral collateral;
	private RealEstate realEstate;
	private CashDeposit cashDeposit;
	private ReturnToRisk returnToRisk;

	@BeforeEach
	void setUp() throws Exception {
		collateral = new Collateral();
		collateral.setCollateralId(101);
		collateral.setCollateralType(CollateralType.REAL_ESTATE);
		collateral.setCurrentValue(1500000.0);
		collateral.setLoanId(1001);
		collateral.setOwnerAddress("Kolkata");
		collateral.setOwnerContact(789456123L);
		collateral.setOwnerName("Adyasha");

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
		returnToRisk.setRatePerSqFt(200.0);

		when(authclient.validate("user1")).thenReturn(true);
		when(service.getCollateral(1001)).thenReturn(returnToRisk);
		when(service.getCollaterals(1001, 100001)).thenReturn(collateral);
		when(service.getCollateral(1002)).thenThrow(CollateralNotFoundException.class);
		when(service.getCollaterals(1002, 100001)).thenThrow(CollateralNotFoundException.class);
	}

	@Test
	void testGetCollateralWithLoanId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCollateral/1001")
				.header("Authorization", "user1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		ReturnToRisk resultRisk = mapper.readValue(result.getResponse().getContentAsString(), ReturnToRisk.class);
		assertEquals(returnToRisk.toString(), resultRisk.toString());
	}

	@Test
	void testGetCollateralWithLoanIdException() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCollateral/1002")
				.header("Authorization", "user1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		// Collateral resutCol =
		// mapper.readValue(result.getResponse().getContentAsString(),
		// Collateral.class);
		assertEquals(500, result.getResponse().getStatus());
	}

	@Test
	void testGetCollateralWithLoanIdAuthenticationException() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCollateral/1001")
				.header("Authorization", "user2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		// Collateral resutCol =
		// mapper.readValue(result.getResponse().getContentAsString(),
		// Collateral.class);
		assertEquals("Cannot find the collateral loan with id: 1001", result.getResponse().getContentAsString());
	}

	@Test
	void testGetCollateralWithLoanIdAndCustomerId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCollateral/1001/100001")
				.header("Authorization", "user1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Collateral resutCol = mapper.readValue(result.getResponse().getContentAsString(), Collateral.class);
		assertEquals(collateral.toString(), resutCol.toString());
	}

	@Test
	void testGetCollateralWithLoanIdAndCustomerIdException() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCollateral/1002/100001")
				.header("Authorization", "user1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		// Collateral resutCol =
		// mapper.readValue(result.getResponse().getContentAsString(),
		// Collateral.class);
		assertEquals(500, result.getResponse().getStatus());
	}

	@Test
	void testGetCollateralWithLoanIdAndCustomerIdAuthenticationException() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCollateral/1001/100001")
				.header("Authorization", "user2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		// Collateral resutCol =
		// mapper.readValue(result.getResponse().getContentAsString(),
		// Collateral.class);
		assertEquals("Cannot find the collateral loan with id: 1001", result.getResponse().getContentAsString());
	}

	@Test
	void testRealEstateCollateral() throws Exception {
		when(service.saveRealEstateCollateral(Mockito.any(RealEstate.class))).thenReturn(true);
		String json = mapper.writeValueAsString(realEstate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/realEstate").header("Authorization", "user1")
				.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(201, result.getResponse().getStatus());
	}

	@Test
	void testRealEstateCollateralBadRequest() throws Exception {
		when(service.saveRealEstateCollateral(realEstate)).thenReturn(false);
		String json = mapper.writeValueAsString(realEstate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/realEstate").header("Authorization", "user1")
				.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Result: " + result.getResponse().getContentAsString());

		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	void testRealEstateCollateralAuthorizationExeption() throws Exception {
		when(service.saveRealEstateCollateral(realEstate)).thenReturn(true);
		String json = mapper.writeValueAsString(realEstate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/realEstate").header("Authorization", "user2")
				.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Result: " + result.getResponse().getContentAsString());

		assertEquals(403, result.getResponse().getStatus());
	}

	@Test
	void testCashDepositCollateral() throws Exception {
		when(service.saveCashDepositCollateral(Mockito.any(CashDeposit.class))).thenReturn(true);
		String json = mapper.writeValueAsString(cashDeposit);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cashDeposit").header("Authorization", "user1")
				.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(201, result.getResponse().getStatus());
	}

	@Test
	void testCashDepositCollateralBadRequest() throws Exception {
		when(service.saveCashDepositCollateral(cashDeposit)).thenReturn(false);
		String json = mapper.writeValueAsString(cashDeposit);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cashDeposit").header("Authorization", "user1")
				.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	void testCashDepositeCollateralAuthorizationExeption() throws Exception {
		when(service.saveCashDepositCollateral(cashDeposit)).thenReturn(true);
		String json = mapper.writeValueAsString(cashDeposit);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cashDeposit").header("Authorization", "user2")
				.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(403, result.getResponse().getStatus());
	}

	@Test
	void testHealthCheck() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/health-check").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200,result.getResponse().getStatus());
	}
}
