package com.cts.training.collateralwebportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
	private int customerId;
	private String customerName;
	private String customerEmailId;
	private String customerPhoneNum;
	private int loanProductId;

}