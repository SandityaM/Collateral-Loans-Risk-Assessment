package com.cognizant.collaterals.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.collaterals.model.CashDeposit;

/**
 * Repository for adding, fetching and manipulating Cash Deposit
 */
@Repository
public interface CashDepositRepository extends CrudRepository<CashDeposit, Integer> {

}
