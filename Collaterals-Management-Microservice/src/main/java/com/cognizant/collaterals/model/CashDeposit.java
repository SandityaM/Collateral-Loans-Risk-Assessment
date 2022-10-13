package com.cognizant.collaterals.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Cash Deposit
 */
@Entity
@Table
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class CashDeposit extends Collateral {

	@ApiModelProperty(value = "Rate of interest of loan.")
	private Double interestRate;

	@ApiModelProperty(value = "Amount deposited.")
	private Double depositAmount;

	@ApiModelProperty(value = "Lock period of loan.")
	private Double lockPeriod;
}
