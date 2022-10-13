package com.cognizant.collaterals.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.collaterals.model.RealEstate;

/**
 * Repository for adding, fetching and manipulating Real Estate
 */
@Repository
public interface RealEstateRepository extends CrudRepository<RealEstate, Integer> {

}
