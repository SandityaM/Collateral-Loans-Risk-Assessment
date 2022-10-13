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
public class CustomerLoanDto {
	@NotNull
	private int loanId;
	@NotNull
	private int customerId;
}
