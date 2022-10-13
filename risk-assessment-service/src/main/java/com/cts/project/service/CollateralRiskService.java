package com.cts.project.service;

import org.springframework.stereotype.Service;

import com.cts.project.exception.LoanNotFoundException;
import com.cts.project.pojo.CollateralRiskPercent;

/**
 * Service interface for Collateral Risk Service
 */
@Service
public interface CollateralRiskService {

	CollateralRiskPercent getRiskAssessment(String token, Integer loanId) throws LoanNotFoundException;

}
