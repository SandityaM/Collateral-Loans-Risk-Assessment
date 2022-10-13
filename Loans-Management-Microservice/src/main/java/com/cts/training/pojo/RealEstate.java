package com.cts.training.pojo;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * RealState POJO Class SubClass of Collatral 
 */
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class RealEstate extends Collateral {

	@ApiModelProperty(value = "Rate per square feet of real estate.")
	private Double ratePerSqFt;

	@ApiModelProperty(value = "Depreciation rate of real estate.")
	private Double depreciationRate;
}
