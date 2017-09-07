package com.sudeep.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeep.rest.model.Customer;
import com.sudeep.rest.service.CustomerService;



@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping("/Customers")
	public List<Customer> getAllCustomers() {
		
		return customerService.getAllCustomers();
	}

	@RequestMapping("/CustomerbyMobile/{search}")
	public List<Customer> getCustomersByMobile( @PathVariable(value="search") String search) {
		
		System.out.println(search);
		return customerService.getCustomerByMobile(search);
	}

}
