package com.cognizant.collaterals.service;

import org.springframework.stereotype.Service;

import com.cognizant.collaterals.model.CashDeposit;
import com.cognizant.collaterals.model.Collateral;
import com.cognizant.collaterals.model.RealEstate;
import com.cognizant.collaterals.model.ReturnToRisk;

/**
 * Service interface for Collateral Management
 */
@Service
public interface CollateralService {

	public ReturnToRisk getCollateral(Integer loanId);

	public boolean saveRealEstateCollateral(RealEstate realEstate);

	public boolean saveCashDepositCollateral(CashDeposit cashDeposit);

	public Collateral getCollaterals(Integer loanId, Integer customerId);
}
