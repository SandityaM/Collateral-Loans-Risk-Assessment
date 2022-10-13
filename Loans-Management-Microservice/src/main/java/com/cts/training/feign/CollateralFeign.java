package com.cts.training.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.training.pojo.CashDeposit;
import com.cts.training.pojo.Collateral;
import com.cts.training.pojo.RealEstate;

/**
 * Collateral Feign used for connection with collateral management
 */
@FeignClient(name = "collateral-management", url = "http://localhost:8000/collateral")
public interface CollateralFeign {

	/**
	 * For accessing the saveRealEstateCollateral
	 * @param token
	 * @param re
	 * @return
	 */
	@PostMapping("/realEstate")
	public ResponseEntity<String> saveRealEstateCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody RealEstate re);

	/**
	 * For Accessing SaveCashDepositCollateral 
	 * @param token
	 * @param cd
	 * @return
	 */
	@PostMapping("/cashDeposit")
	public ResponseEntity<String> saveCashDepositCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody CashDeposit cd);
	
	
	
	
	
	@PostMapping("/collateral")
	public ResponseEntity<String> saveCollateral(@RequestHeader(name="Authorization") String token,
			@RequestBody Collateral collateral);
	
	
	
	
	
}
