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
public class RealEstate {
	@NotNull
	//@NotBlank(message = "Please enter Rate Per Square Ft")
	private Double ratePerSqFt;

	@NotNull
	//@NotBlank(message = "Please enter Depreciation Rate")
	private Double depreciationRate;

	@NotNull
	private Integer collateralId;

	@NotNull
	private Integer loanId;

	private CollateralType collateralType = CollateralType.REAL_ESTATE;
	//@NotBlank(message = "Please Enter Owner's Name")
	private String ownerName;

	//@NotBlank(message = "Please Enter Owner's Address")
	private String ownerAddress;

	@NotNull
	//@NotBlank(message = "Please enter Owner's Phone Number")
	//@Size(max = 10)
	private Long ownerContact;

	@NotNull
	//@NotBlank(message = "Please enter Current Value")
	private Double currentValue;

}

/*
 * private String ownerName;
 * 
 * private String ownerAddress;
 * 
 * private Long ownerContact;
 * 
 * private Double currentValue;
 */
