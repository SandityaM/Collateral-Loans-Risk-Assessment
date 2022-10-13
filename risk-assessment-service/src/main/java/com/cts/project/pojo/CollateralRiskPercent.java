package com.cts.project.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for Collateral Risk Percent
 */
@Getter
@Setter
@ToString
@ApiModel(description = "Model class for Collateral Risk Percent.")
public class CollateralRiskPercent {

	@ApiModelProperty(value = "Risk Percentage Calculated.")
	private Double riskPercent;

	@ApiModelProperty(value = "Collateral Risk Assessed Date.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private String assessedDate;

}
