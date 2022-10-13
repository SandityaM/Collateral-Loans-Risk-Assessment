package com.cts.training.collateralwebportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.training.collateralwebportal.feign.RiskAssessmentClient;
import com.cts.training.collateralwebportal.model.CollateralRiskPercent;
import com.cts.training.collateralwebportal.model.CustomerLoanRisk;

import feign.FeignException;

@Controller
public class RiskAssessmentController {

	@Autowired
	private RiskAssessmentClient riskClient;

	@RequestMapping(value = "/riskassessment", method = RequestMethod.GET)
	public String getRisk(@ModelAttribute("loan") CustomerLoanRisk loan, ModelMap model) {
		return "riskassessment";
	}

	@RequestMapping(value = "/riskassessment", method = RequestMethod.POST)
	public String check(@Validated @ModelAttribute("loan") CustomerLoanRisk loan, BindingResult res, ModelMap model,HttpServletRequest request) throws Exception {
		if (res.hasErrors()) {
			model.put("errorMessage", "Invalid Loan ID!");
			return "redirect:/riskassessment";
		}
		
		String token="Bearer "+request.getSession().getAttribute("token");
		CollateralRiskPercent risk=null;
		try {
			System.out.println("Inside Portal risk assessemnt==================");
			risk=riskClient.getCollateralRisk(token, loan.getLoanId());
			System.out.println("Inside Portal risk assessemnt==================After");
			
			
		}catch (FeignException e) {
			
			// TODO: handle exception
			if(e.getMessage().contains("Loan ID not found")) {
				model.addAttribute("status", "Loan ID not found");
			}
			/*
			 * model.addAttribute("risk", "Loan Id Not Saved"); return "riskassessment";
			 */
			
		}
		
		model.addAttribute("risk", risk);
		return "riskassessment";
		
	}

}
