package com.cts.training.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.training.model.CustomerLoan;

/**
 * Customer Loan Curd Repository 
 */
@Repository
public interface CustomerLoanRepo extends CrudRepository<CustomerLoan, Integer> {

//	@Query("select loan_id from CUSTOMERLOAN where loan_id=(select max(loan_id) from CUSTOMERLOAN)")
//	public Integer findLastLoanId();
}
