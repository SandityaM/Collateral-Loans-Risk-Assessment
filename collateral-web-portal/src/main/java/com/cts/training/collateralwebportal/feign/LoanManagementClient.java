package com.cts.training.collateralwebportal.feign;

 

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.training.collateralwebportal.model.CashDeposit;
import com.cts.training.collateralwebportal.model.CustomerLoan;
import com.cts.training.collateralwebportal.model.LoanApplication;
import com.cts.training.collateralwebportal.model.RealEstate;

 


@FeignClient(name = "loan-management", url = "http://localhost:8100/loan/loan-management")
public interface LoanManagementClient {
    
    @GetMapping(value = "/getLoanDetails/{loanId}/{customerId}")
    public CustomerLoan getLoanDetails(@RequestHeader(name = "Authorization") String token, @PathVariable int loanId,
            @PathVariable int customerId);
    
    @PostMapping(value = "/saveRealEstateCollateral")
    public ResponseEntity<String> saveRealEsateCollateral(@RequestHeader(name = "Authorization") String token,
            @RequestBody RealEstate realEstate);
    
    @PostMapping(value = "/saveCashDepositCollateral")
    public ResponseEntity<String> saveCashDepositCollateral(@RequestHeader(name = "Authorization") String token,
            @RequestBody CashDeposit cashDeposit);
    
    
    @PostMapping(value ="/applyLoan")
    public ResponseEntity<String> applyLoan(@RequestHeader(name = "Authorization") String token,
    		@RequestBody LoanApplication loanApplication);

 
    @GetMapping(value = "/applicationStatus/{applicationId}")
    public LoanApplication getLoanApplicationStatus(@RequestHeader(name = "Authorization") String token, @PathVariable int applicationId);
    

    @GetMapping(value="/approveOrReject")
    public List<LoanApplication> getLoanApplication(@RequestHeader(name = "Authorization") String token);
    
    @PutMapping("/approveLoanApplication/{applicationId}")
    public ResponseEntity<String> approveApplicationStatus(@RequestHeader(name = "Authorization") String token,@PathVariable("applicationId")Integer applicationId);
    
    
    @PutMapping("/rejectLoanApplication/{applicationId}")
    public ResponseEntity<String> rejectApplicationStatus(@RequestHeader(name = "Authorization") String token,@PathVariable("applicationId")Integer applicationId);
    
}