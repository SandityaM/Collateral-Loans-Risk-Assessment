package com.cts.project.pojo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class for return risk collateral
 */
@Getter
@Setter
@ApiOperation(value = "Model class for return risk collaterals.")
public class ReturnToRisk {

	@ApiModelProperty(value = "Type of collateral-CashDeposit/RealEstate")
	private CollateralType collateralType;

	@ApiModelProperty(value = "Rate per square feet of real estate.")
	private Double ratePerSqFt;

	@ApiModelProperty(value = "Rate of interest of loan.")
	private Double interestRate;
}
