package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	
	Customer findById(int id);

}
