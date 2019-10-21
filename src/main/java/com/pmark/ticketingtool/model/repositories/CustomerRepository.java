package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	
	Customer findById(int id);

	Customer findByNameAndOrg(String name, String org);

}
