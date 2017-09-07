package com.sudeep.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.sudeep.rest.model.Customer;
import com.sudeep.rest.repository.CustomerRepository;

@SpringBootApplication
@EntityScan("com.sudeep")
public class SpringRestApiApplication {

	private static final Logger loger = LoggerFactory
			.getLogger(SpringRestApiApplication.class);

	@Autowired
	CustomerRepository customerRepo;

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
			
		SpringApplication.run(SpringRestApiApplication.class, args);

	}

	@PostConstruct
	void postCustomer() {
		loger.info("Sudeep Customer create started...");
		
		Customer customer = new Customer();

		customer.setName("Sudeep Patel");

		try {
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse("01/09/1984");
			loger.info("Date created" + date);

			customer.setCreatedOn(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customer.setOrders(null);
		loger.info(customer.toString());
		customerRepo.save(customer);
		
		loger.info("Customer create successfull...");

	}
}
