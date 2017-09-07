package com.sudeep.rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.sudeep.rest.model.Order;

public interface OrderRespository extends CrudRepository<Order, Long> {

}
