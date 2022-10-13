package com.cts.training.collateralwebportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {
	private int loanProductId;
	private String loanProductName;
	private long maxLoanEligible;
	private double interestRate;
	private int tenure;
	private String collateralType;
}
