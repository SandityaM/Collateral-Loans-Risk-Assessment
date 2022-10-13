package com.cts.training.collateralwebportal.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {

    @NotBlank(message = "Username cannot be empty.")
    private String userName;

    @NotBlank(message = "Password cannot be empty.")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password should contain alpha numeric values")
    private String password;
    
    
    private String authority;
    
}
