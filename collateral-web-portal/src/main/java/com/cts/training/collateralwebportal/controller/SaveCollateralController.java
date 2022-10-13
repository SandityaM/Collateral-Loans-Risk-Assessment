package com.cts.training.collateralwebportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.training.collateralwebportal.model.Loan;
import com.cts.training.collateralwebportal.service.CollateralTypeService;

@Controller
public class SaveCollateralController {
	@Autowired
    CollateralTypeService typeService;
    
    @RequestMapping(value = "/savecollateral", method = RequestMethod.GET)
    public String showCollateral(@ModelAttribute("loan")Loan loan,ModelMap model) {
        return "savecollateral";
    }

}
