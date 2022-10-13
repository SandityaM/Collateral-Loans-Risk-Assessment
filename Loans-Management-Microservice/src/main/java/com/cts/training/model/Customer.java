package com.cts.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer Entity Class
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
@ApiModel(description = "Model Class for Customer Details")
public class Customer {

	@Id
	@ApiModelProperty(value = "Id of the Customer")
	private Integer customerId;

	@ApiModelProperty(value = "Name of the Customer")
	private String customerName;

	@ApiModelProperty(value = "Email Id of the Customer")
	private String customerEmailId;

	@ApiModelProperty(value = "Phone Number of the Customer")
	private String customerPhoneNo;

	@ApiModelProperty(value = "Address of the Customer")
	private String customerAddress;

}
