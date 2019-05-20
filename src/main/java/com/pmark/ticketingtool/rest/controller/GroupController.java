package com.pmark.ticketingtool.rest.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmark.ticketingtool.model.entity.Customer;
import com.pmark.ticketingtool.model.entity.Group;
import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.CustomerRepository;
import com.pmark.ticketingtool.model.repositories.GroupRepository;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import com.pmark.ticketingtool.utility.TicketingException;

@RestController
public class GroupController {
	
	
	
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);

	
	@Inject GroupRepository gRepo;
	@Inject CustomerRepository cRepo;
	@Inject UsersRepository uRepo;
	
	
	@GetMapping(value = "/createGroup")
	public String getMethodName(
			@RequestParam(name="name") String name,
			@RequestParam(name="customer") int customer,
			@RequestParam(name="manager") int manager,
			@RequestParam(name="mail", required=false) String mail
			) throws TicketingException {
		
		
		Customer c = cRepo.findById(customer);
		if(c == null) {
			throw new TicketingException("No customer was found!");
		}
		User u = uRepo.findById(manager);
		if(u == null) {
			throw new TicketingException("No user was found to be the manager for this group");
		}
		
		Group g = new Group();
		g.setCustomer(c);
		g.setManager(u);
		g.setName(name);
		g.setMail(mail);
		
		gRepo.save(g);
		
		
		return JsonFactory.ok();
	}

	
	@ExceptionHandler(TicketingException.class)
	private String handleTicketingException(TicketingException tex) {
		log.error(tex.getMessage());
		
		return JsonFactory.error(tex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	private String handleException(TicketingException tex) {
		log.error(tex.getMessage());
		
		return JsonFactory.error(tex.getMessage());
	}
}
