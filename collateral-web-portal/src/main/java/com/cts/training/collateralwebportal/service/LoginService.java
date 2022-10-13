package com.cts.training.collateralwebportal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.cts.training.collateralwebportal.model.LoginModel;

public interface LoginService {

    Boolean validate(String token);

    String login(LoginModel model);
    public ModelAndView checkStatus(HttpServletRequest request);
    
    //
    public String checkAuthority(LoginModel model);
    
}
