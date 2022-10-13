package com.cts.training.collateralwebportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoan {
	private int loanId;
	private int loanProductId;
	private int customerId;
	private long loanPrincipal;
	private int tenure;
	private double interest;
	private long emi;
	private int collateralId;
}
