package com.cts.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class for Collateral Interest Rate
 */
@Entity
@Table(name = "collateralinterestrate")
@Getter
@Setter
@ApiModel(description = "Model class for Collateral Bank Interest Rate Details.")
public class CollateralInterestRate {

	@Id
	@GeneratedValue
	@ApiModelProperty(value = "Id of the interest.")
	private Integer interestId;

	@ApiModelProperty(value = "Interest Rate of Fixed Deposit.")
	private double interestRate;
}
