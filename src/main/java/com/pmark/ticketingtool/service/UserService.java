package com.pmark.ticketingtool.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Inject private UsersRepository uRepo;
	
	
	public User findUser(String user, String pass) throws Exception  {
		
		User u = uRepo.findFirstByUserAndPass(user, pass);
		
		if(u == null) {
			throw new Exception("No User Found");	
		}
		
		
		return u;
		
	}
	
	
	@ExceptionHandler(Exception.class)
	private void handleException(Exception x) {
		log.error(x.getMessage());
	}

}
