package com.cts.training.collateralwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.training.collateralwebportal.feign.LoanManagementClient;
import com.cts.training.collateralwebportal.model.CashDeposit;

import feign.FeignException;

@Controller
public class CashDepositController {

	
	@Autowired
	private LoanManagementClient loanClient;

	@RequestMapping(value = "/cashdeposit", method = RequestMethod.GET)
	public String showCollateral(@ModelAttribute("cash") CashDeposit cash, ModelMap model) {
		return "cashdeposit";
	}

	@RequestMapping(value = "/cashdeposit", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("cash") CashDeposit cash, BindingResult res, ModelMap model,
			HttpServletRequest request) throws Exception {
		if (res.hasErrors()) {
			model.put("errorMessage", "Invalid Cash Details!");
			return "cashdeposit";
		}
		String token = "Bearer " + (String) request.getSession().getAttribute("token");
		ResponseEntity<String> status = null;
		try {
			status = loanClient.saveCashDepositCollateral(token, cash);
		}  catch (FeignException e) {
			// TODO: handle exception
			if(e.getMessage().contains("Collateral Mismatch")) {
				model.addAttribute("status", "Collateral Mismatch! Try with Valid Data.");
			}
			else if(e.getMessage().contains("Customer Loan Not found with LoanId")) {
				model.addAttribute("status", "Input Mismatch! Try with Valid Data.");
			}
			else if(e.getMessage().contains("Collateral already exists with loan id")) {
				model.addAttribute("status", "Collateral already exists with loan ID");
			}
			return "cashdeposit";
		}

		String body = status.getBody();
		System.err.println(body);
		model.addAttribute("status","Saved Successfully!");

		return "cashdeposit";
	}



}
