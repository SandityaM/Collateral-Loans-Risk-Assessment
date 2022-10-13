package com.cts.training.collateralwebportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }
    
    
    @GetMapping("/customer-home")
    public ModelAndView customerHomePage(){
        ModelAndView mv = new ModelAndView("customer-home");
        return mv;
    }
	
    
    
}
