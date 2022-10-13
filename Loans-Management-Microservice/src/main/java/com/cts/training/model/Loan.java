package com.cts.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Loan Enity Class
 */
@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model Class for the Loan Details")
public class Loan {

	@Id
	@GeneratedValue
	@ApiModelProperty(value = "Id of the LoanProduct")
	private Integer loanProductId;

	@ApiModelProperty(value = "Name of the LoanProduct")
	private String loanProductName;

	@ApiModelProperty(value = "Maximum amount of Loan Eligible for the particular LoanProduct")
	private Double maxLoanEligible;

	@ApiModelProperty(value = "Interest on the LoanProduct")
	private Double interestRate;

	@ApiModelProperty(value = "Tenure of the LoanProduct")
	private Integer tenure;

	@ApiModelProperty(value = "Type of the Collateral")
	private String collateralType;

}
