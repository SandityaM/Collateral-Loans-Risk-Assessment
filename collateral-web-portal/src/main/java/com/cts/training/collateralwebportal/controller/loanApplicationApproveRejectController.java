package com.cts.training.collateralwebportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.training.collateralwebportal.feign.LoanManagementClient;
import com.cts.training.collateralwebportal.model.LoanApplication;

@Controller
public class loanApplicationApproveRejectController {

	
	@Autowired
	private LoanManagementClient loanClient;
	
	@GetMapping(value="/approveOrReject")
	public String getLoans(ModelMap map, HttpServletRequest request) {
		
		System.out.println("===============inside approveOrRejectController in Portal================");
		
        String token = "Bearer "+(String) request.getSession().getAttribute("token");
        
		
		List<LoanApplication> loans =  loanClient.getLoanApplication(token);
		
		System.out.println("===============Fetched list in Controller in Web Portal================");
		
		map.addAttribute("loans",loans);
		return "ApproveReject";
	}
	
	
	@RequestMapping(value = "/approveLoanApplication", method = RequestMethod.GET)
	public String approveLoan(@RequestParam("applicationId") Integer applicationId, HttpServletRequest request) {
		
		System.out.println("===============In approve Loan method in web portal controller================");

		String token = "Bearer "+(String) request.getSession().getAttribute("token");
		
		loanClient.approveApplicationStatus(token, applicationId);
		
		return "redirect:/approveOrReject";
		
	}
	
	@RequestMapping(value = "/rejectLoanApplication", method = RequestMethod.GET)
	public String rejectLoan(@RequestParam("applicationId") Integer applicationId, HttpServletRequest request) {
		
		System.out.println("===============In approve Loan method in web portal controller================");

		String token = "Bearer "+(String) request.getSession().getAttribute("token");
		
		loanClient.rejectApplicationStatus(token, applicationId);
		
		return "redirect:/approveOrReject";
		
	}
    
}
