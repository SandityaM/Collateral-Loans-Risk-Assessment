package com.cts.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Customer Loan Entity Class
 */
@Entity
@Table(name = "customerloan")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model Class for Customer Taking the Loan")
public class CustomerLoan {

	@Id
	@ApiModelProperty(value = "Loan Id for the Customer")
	private Integer loanId;

	@ApiModelProperty(value = "Product Id of the respective Loan")
	private Integer loanProductId;

	@ApiModelProperty(value = "Customer Id of the Loan Bearer")
	private Integer customerId;

	@ApiModelProperty(value = "Loan Principal for the Loan")
	private Double loanPrincipal;

	@ApiModelProperty(value = "Tenure for the Loan")
	private Integer tenure;

	@ApiModelProperty(value = "Interest Rate for the Loan")
	private Double interest;

	@ApiModelProperty(value = "EMI on the Loan")
	private Double emi;

	@ApiModelProperty(value = "Collateral Id for the Loan")
	private Integer collateralId;

}