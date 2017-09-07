package com.sudeep.rest.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudeep.rest.model.Customer;
import com.sudeep.rest.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<Customer>();

		for (Customer customer : customerRepository.findAll()) {
			customers.add(customer);
		}
		return customers;
	}

	public List<Customer> getCustomerByMobile(String search) {
		// TODO Auto-generated method stub
		return customerRepository.findByMobile(search);
	}
}
