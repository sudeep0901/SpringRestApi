package com.sudeep.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sudeep.rest.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	
	  //This is a query method.
   List<Customer> findByMobile(String mobile);
    
    Customer findById(Long id);
}
