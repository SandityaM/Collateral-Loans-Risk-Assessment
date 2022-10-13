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
import com.cts.training.collateralwebportal.model.LoanApplication;
import com.cts.training.collateralwebportal.model.LoanApplicationDto;

import feign.FeignException;



@Controller
public class ApplicationStatusController {
	
	@Autowired
	private LoanManagementClient loanClient;
	
	
	   @RequestMapping(value = "/applicationStatus", method = RequestMethod.GET)
       public String show(@ModelAttribute("loanApp")LoanApplicationDto loanApp,ModelMap model) {
           return "applicationStatus";
       }

  
    
    @RequestMapping(value = "/applicationStatus", method = RequestMethod.POST)
       public String viewStatus(@Valid @ModelAttribute("loanApp")LoanApplicationDto loanApp,
         BindingResult result, ModelMap model,HttpServletRequest request) {
          
           if (result.hasErrors()) {
               model.put("errorMessage", "Invalid Application Id!");
               return "applicationStatus";
           }
           String token = "Bearer "+(String) request.getSession().getAttribute("token");
           LoanApplication loan=null;
          
		    try {
			loan=loanClient.getLoanApplicationStatus(token, loanApp.getApplicationId());
			 System.out.println("================inside Application Status=====================");
			model.addAttribute("loanStatus", loan);
			return "applicationStatus";
		    }
		    catch (FeignException e) {
				// TODO: handle exception
				if(e.getMessage().contains("Loan Application not Found for Id: ")) {
					model.addAttribute("status", "Loan Application not Found!!");
				}
					return "applicationStatus";
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

