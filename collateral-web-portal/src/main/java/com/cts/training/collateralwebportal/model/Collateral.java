package com.cts.training.collateralwebportal.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Collateral {
	private Integer collateralId;

	private Integer loanId;

	private CollateralType collateralType;

	private String ownerName;

	private String ownerAddress;

	private Long ownerContact;

	private Double currentValue;

}