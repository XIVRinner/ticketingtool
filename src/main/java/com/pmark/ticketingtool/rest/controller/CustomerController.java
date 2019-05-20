package com.pmark.ticketingtool.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pmark.ticketingtool.model.entity.Customer;
import com.pmark.ticketingtool.model.repositories.CustomerRepository;
import com.pmark.ticketingtool.utility.JsonFactory;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CustomerController {
	
	@Inject CustomerRepository cRepo;
	
	
	
	
	@GetMapping("/createCustomer")
	public String getMethodName(@RequestParam(name="name") String name, @RequestParam(name="org") 
	String org) {
		
		
		Customer c = new Customer();
		c.setName(name);
		c.setOrg(org);
		
		cRepo.save(c);
		
		
		return JsonFactory.ok();
	}
	

}
