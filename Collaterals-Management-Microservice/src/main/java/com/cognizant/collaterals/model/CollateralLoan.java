package com.cognizant.collaterals.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class for CollateralLoan
 */
@Entity
@Table(name = "collateral_loans")
@Getter
@Setter
@ApiModel(description = "Model class for CollateralLoan")
public class CollateralLoan {

	@Id
	@ApiModelProperty(value = "Id of the collateral loan.")
	private int colLoanId;

	@ApiModelProperty(value = "Id of the loan.")
	private int loanId;

	@ApiModelProperty(value = "Id of the collateral.")
	private Integer collateralId;

	@ApiModelProperty(value = "Value of the collateral.")
	private Double collateralValues;

	@ApiModelProperty(value = "Pledged date of the collateral.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate pledgedDate;
}
