package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.utility.TicketingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.pmark.ticketingtool.model.entity.Customer;
import com.pmark.ticketingtool.model.repositories.CustomerRepository;
import com.pmark.ticketingtool.utility.JsonFactory;

import javax.inject.Inject;


@RestController
@RequestMapping("/private")

public class CustomerController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Inject CustomerRepository cRepo;
	
	
	
	
	@PostMapping("/createCustomer")
	public String getMethodName(@RequestParam(name="name") String name, @RequestParam(name="org") 
	String org) throws TicketingException {
		
		
		Customer c = null;
		
		c = cRepo.findByNameAndOrg("name", "org");
		
		if(c != null)
			throw new TicketingException("This customer already exists!") ;

		c = new Customer.Builder()
				.withName(name)
				.withOrg(org)
				.build();

		log.info("Customer created with name {} for organisation {}", name, org);

		cRepo.save(c);
		
		
		return JsonFactory.ok();
	}
	
	@ResponseBody
	@ExceptionHandler(TicketingException.class)
	private String handleFrontendException(TicketingException ex){

		log.error("Ticketing Exception occurred in CustomerController", ex);

		return JsonFactory.error(ex.getMessage());
	}


}
