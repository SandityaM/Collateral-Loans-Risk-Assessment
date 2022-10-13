package com.cts.project.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.project.exception.LoanNotFoundException;
import com.cts.project.feign.AuthorisationClient;
import com.cts.project.pojo.CollateralRiskPercent;
import com.cts.project.service.CollateralRiskService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test - CollateralRiskController class
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CollateralRiskController.class)
public class CollateralRiskControllerTest {

	@MockBean
	CollateralRiskService service;

	@MockBean
	AuthorisationClient authClient;

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	CollateralRiskPercent riskPercent;

	@Before
	public void init() throws LoanNotFoundException {
		riskPercent = new CollateralRiskPercent();
		riskPercent.setAssessedDate("15-05-2020");
		riskPercent.setRiskPercent(10.0);
		when(authClient.validate("user1")).thenReturn(true);
		when(service.getRiskAssessment("user1", 1001)).thenReturn(riskPercent);
	}

	@Test
	public void testHealthCheck() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/riskManagement/health-check").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200,result.getResponse().getStatus());
	}

	@Test
	public void testGetCollateralRisk() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/riskManagement/getCollateralRisk/1001")
				.header("Authorization", "user1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		CollateralRiskPercent rCollateralRiskPer = mapper.readValue(result.getResponse().getContentAsString(),
				CollateralRiskPercent.class);
		assertEquals(riskPercent.toString(), rCollateralRiskPer.toString());
	}

	@Test
	public void testGetCollateralRiskAuthenticationException() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/riskManagement/getCollateralRisk/1001")
				.header("Authorization", "user").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("Loan ID not found", result.getResponse().getContentAsString());
	}

}
