package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/private")
@Slf4j
public class UserController {
	
    @Inject private UsersRepository uRepo;

	
	@GetMapping(value = "/createUser")
	public String createUser(@RequestParam(name="user") String user,
			@RequestParam(name="pass") String pass,
			@RequestParam(name="permission") int permission) {
			
		User u = new User();
		u.setUser(user);
		u.setPass(MD5Encoder.encode(pass.getBytes()));
		u.setPermission(permission);
		
		uRepo.save(u);


		
		return JsonFactory.ok();
	}

	@GetMapping("/getCurrentUser")
	private String getCurrentUser(){
		Authentication a = SecurityContextHolder.getContext().getAuthentication();

		return a.getName() ;
	}


}
