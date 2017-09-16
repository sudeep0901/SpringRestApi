package com.sudeep.rest.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sudeep.rest.model.Customer;

import com.sudeep.rest.service.CustomerService;
import com.sudeep.rest.util.CustomErrorType;
import com.sudeep.rest.util.Utillities;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	public static final Logger logger = LoggerFactory
			.getLogger(CustomerController.class);
	@Autowired
	CustomerService customerService;

	@RequestMapping("/age/{age}/{page}/{nor}")
	List<Customer> customersPageable(@PathVariable(value = "age") int age,
			@PathVariable(value = "page") int page,
			@PathVariable(value = "nor") int nor) {

		Pageable pageable = new PageRequest(page, nor);
		List<Customer> customers = customerService.findByAgeGreaterThan(age,
				pageable);

		return customers;

	}

	@RequestMapping(value = "/search/{mobile}", method = RequestMethod.GET)
	public List<Customer> searchNative(@PathVariable("mobile") String mobile) {
		logger.info("Searching Customer with mobile{}", mobile);
		String likeExpression = "%" + mobile + "%";

		List<Customer> customers = customerService.searchnative(likeExpression);

		return customers;

	}

	@RequestMapping("/pageable/{page}/{nor}")
	List<Customer> customersPageable(@PathVariable(value = "page") int page,
			@PathVariable(value = "nor") int nor) {

		Pageable pageable = new PageRequest(page, nor);
		List<Customer> customers = customerService
				.paginateAllCustomers(pageable);

		return customers;

	}

	@RequestMapping("/mobile/{mobile}")
	public List<Customer> getByMobile(
			@PathVariable(value = "mobile") String mobile) {

		System.out.println(mobile);
		return customerService.getCustomerByMobile(mobile);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAll() {
		List<Customer> customers = customerService.getAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/allSortbyName", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> allSortbyName() {
		List<Customer> customers = customerService.getAllCustomersSortbyName();
		if (customers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}


	// -------------------Create a
	// Customer-------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", customer.getMobile());

		if (!customerService.checkCustomerByMobile(customer.getMobile())) {

			logger.error(Utillities.valueOf(customerService
					.checkCustomerByMobile(customer.getMobile())));

			logger.error("Unable to create. A User with name {} already exist",
					customer.getMobile());
			return new ResponseEntity(new CustomErrorType(
					"Unable to create. A Customer with mobile"
							+ customer.getMobile() + " already exist."),
					HttpStatus.CONFLICT);
		}

		// try {
		// Date date = new SimpleDateFormat("MM/dd/yyyy").parse("01/09/1984");
		// customer.setDob(date);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// loger.info("Date created" + date);
		//
		// customer.setCreatedOn(date);
		customerService.saveCustomer(customer);

		logger.info("Saving customer....");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/customer/{id}")
				.buildAndExpand(customer.getId()).toUri());
		logger.info(headers.toString());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Customer
	// ------------------------------------------------

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id,
			@RequestBody Customer customer) {
		logger.info("Updating Customer with id {}", id);
		logger.info("Receieved Customer..." + customer.toString());

		Customer currentCustomer = new Customer();

		currentCustomer = customerService.getCustomerById(id);

		if (currentCustomer == null) {
			// logger.info("checking null " + currentCustomer.toString());

			currentCustomer = customerService.getByMobile(customer.getMobile());

		}

		if (currentCustomer == null) {
			logger.error("Unable to update. Customer with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType(
					"Unable to upate. Customer with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentCustomer.setName(customer.getName());
		currentCustomer.setAge(customer.getAge());
		currentCustomer.setMobile(customer.getMobile());
		currentCustomer.setMeasurement(customer.getMeasurement());
		currentCustomer.setDob(customer.getDob());

		logger.info("Updating Customer..." + currentCustomer.toString());
		customerService.updateCustomer(customer);

		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}

	// ------------------- Delete a
	// User-----------------------------------------

	// @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	// public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
	// logger.info("Fetching & Deleting User with id {}", id);
	//
	// User user = userService.findById(id);
	// if (user == null) {
	// logger.error("Unable to delete. User with id {} not found.", id);
	// return new ResponseEntity(new
	// CustomErrorType("Unable to delete. User with id " + id + " not found."),
	// HttpStatus.NOT_FOUND);
	// }
	// userService.deleteUserById(id);
	// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	// }

	// ------------------- Delete All Users-----------------------------

	// @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	// public ResponseEntity<User> deleteAllUsers() {
	// logger.info("Deleting All Users");
	//
	// userService.deleteAllUsers();
	// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	// }

	// }

}
