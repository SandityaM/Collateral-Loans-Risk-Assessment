package com.cts.training.collateralwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.training.collateralwebportal.feign.LoanManagementClient;
import com.cts.training.collateralwebportal.model.CustomerLoan;
import com.cts.training.collateralwebportal.model.CustomerLoanDto;

import feign.FeignException;



@Controller
public class CustomerLoanController {
	
	@Autowired
	private LoanManagementClient loanClient;
	
	
	   @RequestMapping(value = "/customerloan", method = RequestMethod.GET)
       public String show(@ModelAttribute("customloan")CustomerLoanDto customloan,ModelMap model) {
           return "customerloan";
       }

  
    
    @RequestMapping(value = "/customerloan", method = RequestMethod.POST)
       public String submitLoan(@Valid @ModelAttribute("customloan")CustomerLoanDto customloan, 
         BindingResult result, ModelMap model,HttpServletRequest request) {
          
           if (result.hasErrors()) {
               model.put("errorMessage", "Invalid Customer Loan Details!");
               return "customerloan";
           }
           String token = "Bearer "+(String) request.getSession().getAttribute("token");
           CustomerLoan loan=null;
          
		    try {
			loan=loanClient.getLoanDetails(token, customloan.getLoanId(),customloan.getCustomerId());
			 System.out.println("================inside Customer Loan=====================");
			model.addAttribute("loan", loan);
			return "customerloan";
		    }
		    catch (FeignException e) {
				// TODO: handle exception
				if(e.getMessage().contains("Customer Loan Not found with LoanId")) {
					model.addAttribute("status", "Customer Loan Not found!!");
				}
					return "customerloan";
			}
			// TODO: handle exception
		
           //LoanManagementClient
           //catchException
           //${seterrormessage}
           //redirect to the same page
           
           //if(no exception)-
           //details of loan
           //set it to model attribute
           //redirect to same page
           
           //return "customerloan";
           
       }
    
   
}

