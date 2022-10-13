package com.cts.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.training.exception.CollateralTypeNotFoundException;
import com.cts.training.exception.CustomerLoanNotFoundException;
import com.cts.training.exception.CustomerNotFoundException;
import com.cts.training.exception.LoanApplicationNotValidException;
import com.cts.training.exception.LoanNotFoundException;
import com.cts.training.feign.CollateralFeign;
import com.cts.training.model.Customer;
import com.cts.training.model.CustomerLoan;
import com.cts.training.model.Loan;
import com.cts.training.model.LoanApplication;
import com.cts.training.pojo.CashDeposit;
import com.cts.training.pojo.RealEstate;
import com.cts.training.repo.CustomerLoanRepo;
import com.cts.training.repo.CustomerRepo;
import com.cts.training.repo.LoanApplicationRepo;
import com.cts.training.repo.LoanRepo;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * LoanManagementService implementation
 */
@Service
@Slf4j
public class LoanManagementServiceImpl implements LoanManagementService {

	static Integer loadId = 8;
	
	@Autowired
	private CollateralFeign client;

	@Autowired
	private CustomerLoanRepo customerLoanRepo;

	@Autowired
	private LoanRepo loanRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	
	@Autowired
	LoanApplicationRepo loanApplicationRepo;

	private static final String MESSAGE = "Customer Loan Not found with LoanId: ";

	
	/**
	 * Get Loan Details Implimentation
	 */
	@Override
	public CustomerLoan getLoanDetails(int loanId, int customerId) throws CustomerLoanNotFoundException {
		log.info("Get Loan details using loan id and customer id");
		log.info(loanId+"======="+customerId);
		System.out.println("Inside loan management service================");
		CustomerLoan customerLoan = customerLoanRepo.findById(loanId)
		.orElseThrow(() -> new CustomerLoanNotFoundException(MESSAGE + loanId));
		/*
		 * Optional<CustomerLoan> customerLoan=customerLoanRepo.findById(loanId);
		 * System.out.println(customerLoan.get()); if(!customerLoan.isPresent()) { throw
		 * new CustomerLoanNotFoundException(MESSAGE+loanId); }
		 */
		System.out.println(customerLoan);
		if (customerLoan.getCustomerId() != customerId) {
			throw new CustomerLoanNotFoundException(MESSAGE + loanId);
		}
		return customerLoan;
	}
	
	/**
	 * Save RealEstate Implementatiom
	 * 
	 * @throws LoanNotFoundException
	 */
	@Override
	public ResponseEntity<String> saveRealEstate(String token, RealEstate realEstate)
			throws CustomerLoanNotFoundException, LoanNotFoundException {
		log.info("Save Real Estate collateral details");
		System.out.println("===========Saving Real Estate details============= from loan management service"+realEstate);
		CustomerLoan customerLoan = customerLoanRepo.findById(realEstate.getLoanId())
				.orElseThrow(() -> new CustomerLoanNotFoundException(MESSAGE + realEstate.getLoanId()));

		Integer prodId = customerLoan.getLoanProductId();
		Optional<Loan> loanop = loanRepo.findById(prodId);
		if(!loanop.isPresent()){
			throw new LoanNotFoundException("Loan Not found by Id" + prodId);
		}else{
			Loan loan = loanop.get();
			String type = loan.getCollateralType();
		try {
			if (type.equals("REAL_ESTATE")) {

				customerLoan.setCollateralId(realEstate.getCollateralId());
				customerLoanRepo.save(customerLoan);
				return client.saveRealEstateCollateral(token, realEstate);
			} else {
				throw new CollateralTypeNotFoundException("Collateral Mismatch");
			}
		} catch (FeignException e) {
			e.printStackTrace();
			throw new CollateralTypeNotFoundException("Collateral already exists with loan id");
		}
		}
	}
	
	/**
	 * Save Cash Deposit Implementation
	 * 
	 * @throws LoanNotFoundException
	 */
	@Override
	public ResponseEntity<String> saveCashDeposit(String token, CashDeposit cashDeposit)
			throws CustomerLoanNotFoundException, LoanNotFoundException {
		log.info("Save Cash Deposit collateral details");
		CustomerLoan customerLoan = customerLoanRepo.findById(cashDeposit.getLoanId())
				.orElseThrow(() -> new CustomerLoanNotFoundException(MESSAGE + cashDeposit.getLoanId()));

		Integer prodId = customerLoan.getLoanProductId();
		Optional<Loan> loanop = loanRepo.findById(prodId);
		if(!loanop.isPresent()){
			throw new LoanNotFoundException("Loan not Found By Id:" + prodId);
		}else{
			Loan loan = loanop.get();
			String type = loan.getCollateralType();
			try {
				if (type.equals("CASH_DEPOSIT")) {
					customerLoan.setCollateralId(cashDeposit.getCollateralId());
					customerLoanRepo.save(customerLoan);
					return client.saveCashDepositCollateral(token, cashDeposit);
				} else {
					throw new CollateralTypeNotFoundException("Collateral Mismatch");
				}
			} catch (FeignException e) {
				
				throw new CollateralTypeNotFoundException("Collateral already exists with loan id");
			}
		}
	}

	
	@Override
	public ResponseEntity<String> approveLoanApplication(Integer applicationId) throws LoanApplicationNotValidException {
		
		System.out.println("===============In approve Loan method in  LOAN Service================");

		
		LoanApplication loanApplication = loanApplicationRepo.findById(applicationId)
				.orElseThrow(()-> new LoanApplicationNotValidException(MESSAGE + applicationId));
		
		System.out.println("===============Found application In approve Loan method in  LOAN Service================");

		
		loanApplication.setStatus("Approved");
		
		System.out.println("===============Status added================");

		loanApplicationRepo.save(loanApplication);
		
		System.out.println("===============DB updated================");

		CustomerLoan custLoan = new CustomerLoan();
		
		//Integer id = customerLoanRepo.findLastLoanId();
		System.out.println("===============Got Loan Id from Db================");
		custLoan.setLoanId(++loadId);
		custLoan.setCollateralId(null);
		custLoan.setCustomerId(loanApplication.getCustomerId());
		custLoan.setLoanPrincipal(loanApplication.getLoanAmt());
		custLoan.setInterest(loanApplication.getInterestRate());
		custLoan.setTenure(loanApplication.getTenure());
		
		Double p = loanApplication.getLoanAmt();
		Integer t = loanApplication.getTenure();
		Double r = loanApplication.getInterestRate()/100;
		
		Double emi =  ((p * r * (float)Math.pow(1 + r, t)) / (float)(Math.pow(1 + r, t) - 1));
		
		custLoan.setEmi(emi);
		
		customerLoanRepo.save(custLoan);
		
		
		
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> rejectLoanApplication(Integer applicationId) throws LoanApplicationNotValidException {
		LoanApplication loanApplication = loanApplicationRepo.findById(applicationId)
				.orElseThrow(()-> new LoanApplicationNotValidException(MESSAGE + applicationId));
		loanApplication.setStatus("Rejected");
		loanApplicationRepo.save(loanApplication);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Override
    public String saveLoanDetails(LoanApplication loanApplication) throws CustomerNotFoundException {

        log.info("Save Loan Apllication details");

        Integer customerId = loanApplication.getCustomerId();
        Optional<Customer> loanop = customerRepo.findById(customerId);

        if(!loanop.isPresent()){
            throw new CustomerNotFoundException("Customer not Found By Id:" + customerId);
        }else {
            loanApplicationRepo.save(loanApplication);
        }

        return loanApplication.getStatus();

    }
	
	
	@Override
	public LoanApplication getLoanApplicationStatus(Integer applicationId) throws LoanApplicationNotValidException {
	 
		log.info("Get Loan status");
		log.info("======="+applicationId+"=======");
		LoanApplication loan_application = loanApplicationRepo.findById(applicationId)
												.orElseThrow(()-> new LoanApplicationNotValidException("Loan Application not Found for Id: " + applicationId));
	
		return loan_application;
	}

	@Override
    public List<LoanApplication> getLoanApplication(String token) {
       
        List<LoanApplication> listOfLoan = (List<LoanApplication>)loanApplicationRepo.findAll();   
        List<LoanApplication> lists = new ArrayList<>();
        for(LoanApplication obj : listOfLoan) {
            if(obj.getStatus().equalsIgnoreCase("Pending")) {
                lists.add(obj);
            }
        }
        return lists;
    }
	
}
