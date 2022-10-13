package com.cts.training.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.exception.CustomerLoanNotFoundException;
import com.cts.training.exception.CustomerNotFoundException;
import com.cts.training.exception.LoanApplicationNotValidException;
import com.cts.training.exception.LoanNotFoundException;
import com.cts.training.feign.AuthorisationClient;
import com.cts.training.model.CustomerLoan;
import com.cts.training.model.LoanApplication;
import com.cts.training.pojo.CashDeposit;
import com.cts.training.pojo.RealEstate;
import com.cts.training.service.LoanManagementService;

/**
 * This Class is marked as Rest Controller LoanManagementController
 * 
 * @ResquestMapping /loan-management
 */
@RestController
@RequestMapping(value = "/loan-management")
public class LoanManagementController {

	@Autowired
	private LoanManagementService loanService;

	@Autowired
	private AuthorisationClient authClient;

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

	/**
	 * getLoanDetails based on LoanId and CustomerId
	 * 
	 * @param token
	 * @param loanId
	 * @param customerId
	 * @return
	 * @throws CustomerLoanNotFoundException
	 */
	@GetMapping(value = "/getLoanDetails/{loanId}/{customerId}")
	public CustomerLoan getLoanDetails(@RequestHeader(name = "Authorization") String token, @PathVariable int loanId,
			@PathVariable int customerId) throws CustomerLoanNotFoundException {
		System.out.println("===============inside getloandetails================");
		if (authClient.validate(token)) {
			return loanService.getLoanDetails(loanId, customerId);
		} else {
			throw new CustomerLoanNotFoundException(
					"Customer Loan Found With LoanId:" + loanId + " But Not Found With CustomerId:" + customerId);
		}
	}

	/**
	 * This method saveRealEstateCollateral based on realEstate Object as parameter
	 * 
	 * @param token
	 * @param realEstate
	 * @return
	 * @throws CustomerLoanNotFoundException
	 * @throws LoanNotFoundException
	 */
	@PostMapping(value = "/saveRealEstateCollateral")
	public ResponseEntity<String> saveRealEsateCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody RealEstate realEstate) throws CustomerLoanNotFoundException, LoanNotFoundException {
		if (authClient.validate(token)) {
			return loanService.saveRealEstate(token, realEstate);
		} else {
			return new ResponseEntity<>("Invalid token", HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * This method saveCashDepositeCollateral based on cashEstate Object as
	 * parameter
	 * 
	 * @param token
	 * @param cashDeposit
	 * @return
	 * @throws CustomerLoanNotFoundException
	 * @throws LoanNotFoundException
	 */
	@PostMapping(value = "/saveCashDepositCollateral")
	public ResponseEntity<String> saveCashDepositCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody CashDeposit cashDeposit) throws CustomerLoanNotFoundException, LoanNotFoundException {
		if (authClient.validate(token)) {
			return loanService.saveCashDeposit(token, cashDeposit);
		} else {
			return new ResponseEntity<>("Invalid token", HttpStatus.FORBIDDEN);
		}
	}
	
	

	
	
	
	
	
	/**
     * Apply For Loan
     *
     * @param token
     * @param loanApplication
     * @return
     * @throws CustomerNotFoundException
     */
    @PostMapping(value = "/applyLoan")
    public String applyLoan(@RequestHeader(name = "Authorization") String token, @RequestBody LoanApplication loanApplication)
            throws CustomerNotFoundException {
        System.out.println("===============inside applyLoan================");
        if (authClient.validate(token)) {
            return loanService.saveLoanDetails(loanApplication);
        } else {
            throw new CustomerNotFoundException(
                    "Customer Not Found");
        }
    }
	

    @GetMapping(value="/applicationStatus/{applicationId}")
    public LoanApplication getLoanApplicationStatus(@RequestHeader(name = "Authorization")String token , @PathVariable Integer applicationId) throws LoanApplicationNotValidException {
      
    	if (authClient.validate(token)) {
          return loanService.getLoanApplicationStatus(applicationId);
    	} else {
          throw new LoanApplicationNotValidException("Loan not found for this : "+applicationId);
      }
    }
    
  
  
  @GetMapping(value="/approveOrReject")
  public List<LoanApplication> getLoanApplication(@RequestHeader(name = "Authorization") String token){
	  
	  System.out.println("===============inside approveOrRejectController in Loan Management Service================");
	  
	  if (authClient.validate(token)) {
          return loanService.getLoanApplication(token);
        } else {
          return null;
      }
  }
  
//  @PutMapping("/approveLoanApplication/{applicationId}")
//	public String approveLoan(@RequestHeader(name = "Authorization") String token,@RequestParam("applicationId") Integer applicationId) {
//		
//	  
//		
//	}
//	
//	@PutMapping("/rejectLoanApplication/{applicationId}")
//	public String rejectLoan(@RequestHeader(name = "Authorization") String token,@RequestParam("applicationId") Integer applicationId) {
//		
//		
//		
//	}
	
	
  @PutMapping(value="approveLoanApplication/{applicationId}")
  public ResponseEntity<String> approveApplicationStatus(@RequestHeader(name = "Authorization")String token , @PathVariable Integer applicationId) throws LoanApplicationNotValidException {
      
		System.out.println("===============In approve Loan method in  LOAN Controller================");
	  
	  if (authClient.validate(token)) {
          return loanService.approveLoanApplication(applicationId);
        } else {
          throw new LoanApplicationNotValidException("Loan not found for this : "+applicationId);
      }
  }
 
 
  @PutMapping(value="rejectLoanApplication/{applicationId}")
  public ResponseEntity<String> rejectApplicationStatus(@RequestHeader(name = "Authorization")String token , @PathVariable Integer applicationId) throws LoanApplicationNotValidException {
      
		System.out.println("===============In reject Loan method in  LOAN Controller================");

	  
	  if (authClient.validate(token)) {
          return loanService.rejectLoanApplication(applicationId);
        } else {
          throw new LoanApplicationNotValidException("Loan not found for this : "+applicationId);
      }
  }
  
  
}
