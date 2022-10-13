package com.cts.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.project.model.CollateralRisk;

/**
 * Repository for adding, fetching and manipulating Collateral Risk Repo
 */
@Repository
public interface CollateralRiskRepo extends CrudRepository<CollateralRisk, Integer> {

}
