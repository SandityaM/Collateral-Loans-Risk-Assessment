package com.cts.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class for Collateral Market value
 */
@Entity
@Table(name = "collateralmarketvalue")
@Getter
@Setter
@ApiModel(description = "Model class for Collateral Market Value Details.")
@JsonPropertyOrder({ "ratePerSqrFeet" })
public class CollateralMarketValue {

	@Id
	@ApiModelProperty(value = "Id of the RatePerSqrFeet.")
	private Integer rateId;

	@ApiModelProperty(value = "Rate Per Square Feet.")
	private Double ratePerSqrFeet;

}
