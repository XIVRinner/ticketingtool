package com.pmark.ticketingtool.rest.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import com.pmark.ticketingtool.utility.JsonFactory;

@RestController
public class UserController {
	
    @Inject private UsersRepository uRepo;

	
	@GetMapping(value = "/createUser")
	public String getMethodName(@RequestParam(name="user") String user,
			@RequestParam(name="pass") String pass,
			@RequestParam(name="permission") int permission) {
			
		User u = new User();
		u.setUser(user);
		u.setPass(pass);
		u.setPermission(permission);
		
		uRepo.save(u);
		
		
		return JsonFactory.ok();
	}

}
