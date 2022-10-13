package com.cts.training.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "loanApplication")
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model Class for the Loan Application Details")
 
public class LoanApplication {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Id of the Loan Application")
    private int applicationId;

    @ApiModelProperty(value = "Id of the customer")
    private int customerId;

//    @ApiModelProperty(value = "Id of the LoanProduct")
//    private int loanProductId;

    @ApiModelProperty(value = "Loan Amount value")
    private double loanAmt;

    @ApiModelProperty(value = "Tenure for the Loan")
    private Integer tenure;
 
    @ApiModelProperty(value = "Interest Rate for the Loan")
    private Double interestRate;
    
    @ApiModelProperty(value = "Status of the Loan Application")
    private String status;
    
    @ApiModelProperty(value = "Collateral Type of the Loan Application")
    private String collateralType;
    
    
    @ApiModelProperty(value = "Value of Colateral")
    private Double collateralValue;
    
 
    
}