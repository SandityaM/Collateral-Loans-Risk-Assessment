package com.cts.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class for Collateral Risk
 */
@Entity
@Table(name = "collateralRisk")
@Getter
@Setter
@ApiModel(description = "Model class for Collateral Risk.")
public class CollateralRisk {

	@Id
	@GeneratedValue
	@ApiModelProperty(value = "Risk ID")
	private int riskId;

	@ApiModelProperty(value = "Risk Percentage Calculated.")
	private double riskPercent;

	@ApiModelProperty(value = "Collateral Risk Assessed Date.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date assessedDate;

	@ApiModelProperty(value = "Current Market Value of Collateral.")
	private double marketValue;
}
