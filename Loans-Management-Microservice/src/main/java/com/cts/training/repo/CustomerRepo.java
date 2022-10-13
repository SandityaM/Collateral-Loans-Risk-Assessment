package com.cts.training.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.training.model.Customer;
/**
 * Customer Entity Curd Curd Repository
 */
@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

}
