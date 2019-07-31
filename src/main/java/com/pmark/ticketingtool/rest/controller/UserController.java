package com.pmark.ticketingtool.rest.controller;

import javax.inject.Inject;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import sun.security.provider.MD5;

@RestController
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

	@GetMapping("/testJSON")
	private String testJSON() throws Exception{
		User user = new User();
		user.setPass("asd");
		user.setUser("admin");
		return 	user.toJson().toString();

	}

}
