package com.pmark.ticketingtool.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.pmark.ticketingtool.model.entity.Ticket;
import com.pmark.ticketingtool.utility.TicketingException;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Inject private UsersRepository uRepo;

	@Value("${ticketingtool.devmode:false}")
	private boolean devmode;
	
	
	public User findUser(String user, String pass) throws Exception  {
		
		User u = uRepo.findFirstByUserAndPass(user, pass);
		
		if(u == null) {
			throw new TicketingException("No User Found");
		}
		
		
		return u;
		
	}
	
	
	@ExceptionHandler(Exception.class)
	private void handleException(Exception x) {
		log.error("Regular exception during UserSerivce: ", x);
	}

	@ResponseBody
	@ExceptionHandler(TicketingException.class)
	private void handleTicketingException (TicketingException x) { log.error("Ticketing exception occured : ", x);}

}
