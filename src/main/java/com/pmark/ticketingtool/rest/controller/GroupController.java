package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Customer;
import com.pmark.ticketingtool.model.entity.Group;
import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.CustomerRepository;
import com.pmark.ticketingtool.model.repositories.GroupRepository;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import com.pmark.ticketingtool.utility.TicketingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/private")
@Slf4j
public class GroupController {
	
	

	
	@Inject GroupRepository gRepo;
	@Inject CustomerRepository cRepo;
	@Inject UsersRepository uRepo;
	
	
	@GetMapping(value = "/createGroup")
	public String createGroup(
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

	@GetMapping("/getAllGroup")
	private String getAllGroup() throws Exception {
		List<Group> groups = (List<Group>) gRepo.findAll();

		return JsonFactory.toJArray(groups);
	}

	
	@ExceptionHandler(TicketingException.class)
	private String handleTicketingException(TicketingException tex) {
		log.error(tex.getMessage());
		
		return JsonFactory.error(tex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	private String handleException(Exception tex) {
		log.error(tex.getMessage());
		
		return JsonFactory.error(tex.getMessage());
	}
}
