package com.pmark.ticketingtool.config;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.service.UserService;

@Component
public class AuthenticationConfiguration implements AuthenticationProvider {
	
	@Inject UserService uService;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		String name = authentication.getName();
		String pass = authentication.getCredentials().toString();
		
		User u = null;
		try {
			u = uService.findUser(name, pass);
		} catch (Exception e) {
			
		}
		
		if(u.getPass().equals(pass) && u.getUser().equals(name)) {
			return new UsernamePasswordAuthenticationToken(name, pass);
		}
		
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	

}
