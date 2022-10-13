package com.cts.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.project.exception.LoanNotFoundException;
import com.cts.project.feign.AuthorisationClient;
import com.cts.project.pojo.CollateralRiskPercent;
import com.cts.project.service.CollateralRiskService;

/**
 * Controller class for CollateralRiskService
 */
@RestController
@RequestMapping("/riskManagement")
public class CollateralRiskController {

	@Autowired
	CollateralRiskService collateralRiskService;

	@Autowired
	private AuthorisationClient authorisationClient;

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

	/**
	 * get Collateral Risk using loan id
	 * 
	 * @param token
	 * @param loanId
	 * @return CollateralRiskPercent
	 * @throws LoanNotFoundException
	 */
	@GetMapping("/getCollateralRisk/{loanID}")
	public CollateralRiskPercent getCollateralRisk(@RequestHeader(name = "Authorization") String token,
			@PathVariable("loanID") Integer loanId) throws LoanNotFoundException {
		System.out.println("Inside getCollateral Risk Risk microserivce================");
		if (authorisationClient.validate(token)) {
			return collateralRiskService.getRiskAssessment(token, loanId);
		} else
			throw new LoanNotFoundException("Loan ID not found");
	}
}
