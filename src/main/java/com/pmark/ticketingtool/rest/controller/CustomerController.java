package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Customer;
import com.pmark.ticketingtool.model.repositories.CustomerRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import com.pmark.ticketingtool.utility.TicketingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
@RequestMapping("/private")
@Slf4j
public class CustomerController {
	

	@Inject CustomerRepository cRepo;
	
	
	
	
	@PostMapping("/createCustomer")
	public String getMethodName(@RequestParam(name="name") String name, @RequestParam(name="org") 
	String org) throws TicketingException {
		
		
		Customer c = null;
		
		c = cRepo.findByNameAndOrg("name", "org");
		
		if(c != null)
			throw new TicketingException("This customer already exists!") ;

		c = Customer.builder()
				.name(name)
				.org(org)
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
