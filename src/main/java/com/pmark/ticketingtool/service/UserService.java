package com.pmark.ticketingtool.service;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.UsersRepository;
import com.pmark.ticketingtool.utility.TicketingException;
import com.pmark.ticketingtool.utility.Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.rsa.crypto.RsaSecretEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class UserService {
	

	
	@Inject private UsersRepository uRepo;


	private boolean devmode;
	
	
	public User findUser(String user, String pass) throws Exception  {
		String coded = Tools.generateHash(pass, 0);
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

		User new_user = User.builder().user(user).pass(coded).permission(1).build();
		uRepo.save(new_user);

		return true;
	}
	public boolean createUser(String user, String pass, int perm) {
		String coded = MD5Encoder.encode(pass.getBytes());

		User u = uRepo.findFirstByUser(user);
		if(u != null)
			return false;

		User new_user = User.builder().user(user).pass(coded).permission(perm).build();
		uRepo.save(new_user);

		return true;
	}

	@PostConstruct
	private void init(){
		User u = uRepo.findFirstByUser("admin");
		if(isNull(u)){
			log.info("###############################################");
			log.info("######                                   ######");
			log.info("#  Default Admin was not found... creating... #");
			log.info("######                                   ######");
			log.info("###############################################");

			String pass = "admin";

			User defa = User.builder().pass(Tools.generateHash(pass, 0)).permission(1).user("admin").build();
			uRepo.save(defa);

			log.info("###############################################");
			log.info("######                                   ######");
			log.info("#        Default ADMIN was created            #");
			log.info("######                                   ######");
			log.info("###############################################");
		}
		else{
			log.info("Default user was found!");
		}

	}
	
	@ExceptionHandler(Exception.class)
	private void handleException(Exception x) {
		log.error("Regular exception during UserSerivce: ", x);
	}

	@ResponseBody
	@ExceptionHandler(TicketingException.class)
	private void handleTicketingException (TicketingException x) { log.error("Ticketing exception occured : ", x);}



}
