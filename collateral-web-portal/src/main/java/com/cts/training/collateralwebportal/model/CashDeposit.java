package com.cts.training.collateralwebportal.model;

import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashDeposit {
	@NotNull
	private Integer collateralId;

	@NotNull
	private Integer loanId;
	
	private CollateralType collateralType = CollateralType.CASH_DEPOSIT;
	
	//@NotBlank(message="Please Enter Owner's Name")
	private String ownerName;
	
	//@NotBlank(message="Please Enter Owner's Address")
	private String ownerAddress;

	@NotNull
	//@NotBlank(message = "Please enter Owner's Phone Number")
	//@Size(max = 10)
	private Long ownerContact;

	@NotNull
	//@NotBlank(message = "Please enter Current Value")
	private Double currentValue;

	@NotNull
	//@NotBlank(message = "Please enter Interest Rate")
	private Double interestRate;
	
	@NotNull
	//@NotBlank(message = "Please enter Deposit Amount")
	private Double depositAmount;
	
	@NotNull
	//@NotBlank(message = "Please enter Lock Period")
	private Double lockPeriod;

}
