package com.cts.training.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.training.exception.CustomerLoanNotFoundException;
import com.cts.training.exception.CustomerNotFoundException;
import com.cts.training.exception.LoanApplicationNotValidException;
import com.cts.training.exception.LoanNotFoundException;
import com.cts.training.model.CustomerLoan;
import com.cts.training.model.LoanApplication;
import com.cts.training.pojo.CashDeposit;
import com.cts.training.pojo.RealEstate;

/**
 * LoanManagementService Interface for the loan management functionality
 */
public interface LoanManagementService {
	/**
	 * For Fetching the loan details
	 * @param loanId
	 * @param customerId
	 * @return CustomerLoan
	 * @throws CustomerLoanNotFoundException
	 */
	public CustomerLoan getLoanDetails(int loanId, int customerId) throws CustomerLoanNotFoundException;

	/**
	 * For Saving RealEstate Estate 
	 * @param token
	 * @param realEstate
	 * @return ResponseEntity/Status
	 * @throws CustomerLoanNotFoundException
	 * @throws LoanNotFoundException
	 */
	public ResponseEntity<String> saveRealEstate(String token, RealEstate realEstate)
			throws CustomerLoanNotFoundException, LoanNotFoundException;
	/**
	 * For Saving CashDeposit Estate
	 * @param token
	 * @param cashDeposit
	 * @return
	 * @throws CustomerLoanNotFoundException
	 * @throws LoanNotFoundException
	 */
	public ResponseEntity<String> saveCashDeposit(String token, CashDeposit cashDeposit)
			throws CustomerLoanNotFoundException, LoanNotFoundException;
	
	

	public ResponseEntity<String> approveLoanApplication(Integer applicationId) 
			throws LoanApplicationNotValidException;
	
	
	
	public ResponseEntity<String> rejectLoanApplication(Integer applicationId) 
			throws LoanApplicationNotValidException;
	
	
	
	public String saveLoanDetails(LoanApplication loanApplication) throws CustomerNotFoundException;
	
	
	public LoanApplication getLoanApplicationStatus(Integer applicationId) throws LoanApplicationNotValidException;
	
	
	public List<LoanApplication> getLoanApplication(String token);
	
	
}
