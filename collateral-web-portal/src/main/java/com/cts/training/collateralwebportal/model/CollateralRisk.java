package com.cts.training.collateralwebportal.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CollateralRisk {
	private int riskId;;

	private double riskPercent;

	private Date assessedDate;

	private double marketValue;

	private double sanctionedValue;

}