package com.cognizant.collaterals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.collaterals.exceptions.CollateralNotFoundException;
import com.cognizant.collaterals.feign.AuthorisationClient;
import com.cognizant.collaterals.model.CashDeposit;
import com.cognizant.collaterals.model.Collateral;
import com.cognizant.collaterals.model.RealEstate;
import com.cognizant.collaterals.model.ReturnToRisk;
import com.cognizant.collaterals.service.CollateralService;

import io.swagger.annotations.ApiOperation;

/**
 * Controller class for collateral service
 */
@RestController
@CrossOrigin
public class CollateralController {

	@Autowired
	AuthorisationClient authorisationClient;

	@Autowired
	private CollateralService collateralService;

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

	/**
	 * get Collateral using Loan id
	 * 
	 * @param token
	 * @param loanId
	 * @return ReturnToRisk
	 */

	@GetMapping("/getCollateral/{loanId}")
	@ApiOperation(value = "getCollaterals", notes = "get the details of collateral", httpMethod = "GET", response = ReturnToRisk.class)
	public ReturnToRisk getCollateralWithLoanId(@RequestHeader(name = "Authorization") String token,
			@PathVariable Integer loanId) {
		System.out.println("Checking Validation=====================token="+token);
		if (authorisationClient.validate(token)) {
			System.out.println("Checking Validation=====================2 "+token);
			return collateralService.getCollateral(loanId);
		} else {
			throw new CollateralNotFoundException("Cannot find the collateral loan with id: " + loanId);
		}
	}

	/**
	 * get Collateral using loan id and customer id
	 * 
	 * @param token
	 * @param loanId
	 * @param customerId
	 * @return Collateral
	 */
	@GetMapping("/getCollateral/{loanId}/{customerId}")
	@ApiOperation(value = "getCollaterals", notes = "get the details of collateral", httpMethod = "GET", response = ResponseEntity.class)
	public Collateral getCollaterals(@RequestHeader(name = "Authorization") String token, @PathVariable Integer loanId,
			@PathVariable Integer customerId) {
		if (authorisationClient.validate(token)) {
			return collateralService.getCollaterals(loanId, customerId);
		} else {
			throw new CollateralNotFoundException("Cannot find the collateral loan with id: " + loanId);
		}
	}

	/**
	 * save Collateral - Real Estate
	 * 
	 * @param token
	 * @param realEstate
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/realEstate")
	@ApiOperation(value = "saveRealEstateCollateral", notes = "save a new real estate collateral ", httpMethod = "POST", response = ResponseEntity.class)
	public ResponseEntity<String> saveRealEstateCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody RealEstate realEstate) {
		if (authorisationClient.validate(token)) {
			if (collateralService.saveRealEstateCollateral(realEstate)) {
				return new ResponseEntity<>("Collaterals Saved", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Collaterals Not Saved", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * save Collateral - Cash Deposit
	 * 
	 * @param token
	 * @param cashDeposit
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/cashDeposit")
	@ApiOperation(value = "saveCashDepositCollateral", notes = "save a new cash deposit collateral ", httpMethod = "POST", response = ResponseEntity.class)
	public ResponseEntity<String> saveCashDepositCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody CashDeposit cashDeposit) {
		if (authorisationClient.validate(token)) {
			if (collateralService.saveCashDepositCollateral(cashDeposit)) {
				return new ResponseEntity<>("Collaterals Saved", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Collaterals Not Saved", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Invalid Token", HttpStatus.FORBIDDEN);
		}
	}
}
