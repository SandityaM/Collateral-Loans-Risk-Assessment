package com.cts.training.collateralwebportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.training.collateralwebportal.feign.LoanManagementClient;
import com.cts.training.collateralwebportal.feign.RiskAssessmentClient;
import com.cts.training.collateralwebportal.model.CollateralRiskPercent;
import com.cts.training.collateralwebportal.model.CustomerLoanRisk;
import com.cts.training.collateralwebportal.model.LoanApplication;

import feign.FeignException;

@Controller
public class LoanApplicationController {

	static int loanId = 0;
	
	@Autowired
	private LoanManagementClient client;

	@RequestMapping(value = "/applyLoan", method = RequestMethod.GET)
	public String getRisk(@ModelAttribute("loanApplication") LoanApplication loanApplication, ModelMap model) {
		return "loanApplication";
	}

	
	@RequestMapping(value = "/applyLoan", method = RequestMethod.POST)
	public String check(@Validated @ModelAttribute("loanApplication") LoanApplication loanApplication, BindingResult res, ModelMap model,HttpServletRequest request) throws Exception {
		if (res.hasErrors()) {
			model.put("errorMessage", "Invalid");
			return "redirect:/applyLoan";
		}
		
		String token="Bearer "+request.getSession().getAttribute("token");
		ResponseEntity<String> status = null;
		try {
			status = client.applyLoan(token, loanApplication);
			
			System.out.println("Inside Loan Mamagement ==================");
			
		}catch (FeignException e) {
			
			// TODO: handle exception
			if(e.getMessage().contains("Customer Not Found")) {
				model.addAttribute("status", "Customer not found");
			}
			/*
			 * model.addAttribute("risk", "Loan Id Not Saved"); return "riskassessment";
			 */
			return "loanApplication";
		}
		
		String body = status.getBody();
		System.err.println(body);
		model.addAttribute("status","Loan Applied Successfully! Application Id :" + (++loanId));

		return "loanApplication";
		
	}
	
}
