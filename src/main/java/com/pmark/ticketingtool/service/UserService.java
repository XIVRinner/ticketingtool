package com.pmark.ticketingtool.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Inject private UsersRepository uRepo;

	@Value("${ticketingtool.devmode:false}")
	private boolean devmode;
	
	
	public User findUser(String user, String pass) throws Exception  {
		
		User u = uRepo.findFirstByUserAndPass(user, pass);
		
		if(u == null) {
			throw new Exception("No User Found");	
		}
		
		
		return u;
		
	}

	@PostConstruct
	private void init(){
		User u = uRepo.findFirstByUser("admin");
		if(u == null && devmode){
			log.info("Default admin user not found...");
			u = new User();
			u.setUser("admin");
			u.setPermission(1);
			u.setPass(MD5Encoder.encode("admin".getBytes()));
			uRepo.save(u);
		}
		else{
			log.info("Default admin user found and/or development mode is on.");
		}



	}
	
	
	@ExceptionHandler(Exception.class)
	private void handleException(Exception x) {
		log.error("Regular exception during UserSerivce: ", x);
	}

}
