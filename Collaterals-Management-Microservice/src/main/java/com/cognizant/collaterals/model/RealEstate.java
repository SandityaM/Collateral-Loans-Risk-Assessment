package com.cognizant.collaterals.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Real Estate
 */
@Entity
@Table
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class RealEstate extends Collateral {

	@ApiModelProperty(value = "Rate per square feet of real estate.")
	private Double ratePerSqFt;

	@ApiModelProperty(value = "Depreciation rate of real estate.")
	private Double depreciationRate;
}
