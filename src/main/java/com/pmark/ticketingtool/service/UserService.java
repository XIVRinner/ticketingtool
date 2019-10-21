package com.pmark.ticketingtool.service;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import com.pmark.ticketingtool.utility.TicketingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Service
@Slf4j
public class UserService {
	

	
	@Inject private UsersRepository uRepo;

	@Value("${ticketingtool.devmode:false}")
	private boolean devmode;
	
	
	public User findUser(String user, String pass) throws Exception  {
		String coded = MD5Encoder.encode(pass.getBytes());
		User u = uRepo.findFirstByUserAndPass(user, coded);
		
		if(u == null) {
			throw new TicketingException("No User Found");
		}
		
		
		return u;
		
	}
	public boolean createUser(String user, String pass) {
		String coded = MD5Encoder.encode(pass.getBytes());

		User u = uRepo.findFirstByUser(user);
		if(u != null)
			return false;

		User new_user = new User.Builder().withUser(user).withPass(coded).withPermission(1).build();
		uRepo.save(new_user);

		return true;
	}
	public boolean createUser(String user, String pass, int perm) {
		String coded = MD5Encoder.encode(pass.getBytes());

		User u = uRepo.findFirstByUser(user);
		if(u != null)
			return false;

		User new_user = new User.Builder().withUser(user).withPass(coded).withPermission(perm).build();
		uRepo.save(new_user);

		return true;
	}
	
	@ExceptionHandler(Exception.class)
	private void handleException(Exception x) {
		log.error("Regular exception during UserSerivce: ", x);
	}

	@ResponseBody
	@ExceptionHandler(TicketingException.class)
	private void handleTicketingException (TicketingException x) { log.error("Ticketing exception occured : ", x);}

}
