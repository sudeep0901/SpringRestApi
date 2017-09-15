package com.sudeep.rest.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudeep.rest.model.Customer;
import com.sudeep.rest.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceInterface {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;

	}

	
	// Paginate all Customers
		public List<Customer> paginateAllCustomers(Pageable pageable) {

			List<Customer> customers = new ArrayList<Customer>();

			for (Customer customer : customerRepository.findAllOrderByCreatedOn(pageable)) {
				customers.add(customer);
			}
			return customers;
		}


	
	// Get all Customers
	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<Customer>();

		for (Customer customer : customerRepository.findAll()) {
			customers.add(customer);
		}
		return customers;
	}

	// by Name
	@Override
	public Customer getCustomerByName(String name) {
		// TODO Auto-generated method stub
		return customerRepository.findByName(name);
	}

	public List<Customer> getCustomerByMobile(String mobile) {
		// TODO Auto-generated method stub
		return customerRepository.findByMobile(mobile);
	}

	public Customer getByMobile(String mobile) {
		// TODO Auto-generated method stub
		return customerRepository.getByMobile(mobile);
	}

	public Customer getCustomerById(long id) {

		return customerRepository.findById(id);
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub

		customerRepository.save(customer);

	}

	@Override
	public void deleteCustomerById(long id) {
		// TODO Auto-generated method stub
		customerRepository.delete(id);
	}

	@Override
	public void deleteByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.delete(customer);
	}

	@Override
	public void deleteAllCustomers() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCustomerExist(long id) {
		// TODO Auto-generated method stub

		return customerRepository.exists(id);

	}

	@Override
	public boolean checkCustomerByMobile(String mobile) {
		// TODO Auto-generated method stub
		return customerRepository.CheckExistsByMobile(mobile);
	}
}
