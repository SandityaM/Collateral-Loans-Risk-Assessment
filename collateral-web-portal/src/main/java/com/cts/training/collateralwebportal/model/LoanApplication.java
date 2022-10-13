package com.cts.training.collateralwebportal.model;


import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {

	
		@NotNull
		private int applicationId;

		@NotNull
	    private int customerId;

		
//	    @ApiModelProperty(value = "Id of the LoanProduct")
//	    private int loanProductId;
	    
		@NotNull
	    private double loanAmt;
		@NotNull
	    private Integer tenure;
		@NotNull
		private Double interestRate;
		
        private String status="Pending";
        
        private String collateralType;
        
        private Double collateralValue;
	 
}
