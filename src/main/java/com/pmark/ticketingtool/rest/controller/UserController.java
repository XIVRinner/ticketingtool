package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Group;
import com.pmark.ticketingtool.model.entity.GroupMember;
import com.pmark.ticketingtool.model.entity.User;
import com.pmark.ticketingtool.model.repositories.GroupMemberRepository;
import com.pmark.ticketingtool.model.repositories.GroupRepository;
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

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/private")
@Slf4j
public class UserController {
	
    @Inject private UsersRepository uRepo;

    @Inject private GroupRepository groupRepository;

    @Inject private GroupMemberRepository groupMemberRepository;

	
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

	@GetMapping("/getUserGroup")
	private String getUserGroup(@RequestParam(name = "username") String username) throws Exception{

		log.info("QUERY for {} user groups", username);
		User u = uRepo.findFirstByUser(username);
		requireNonNull(u);
		List<GroupMember> gm = groupMemberRepository.findAllByUser(u);
		if(gm.size() == 0 || isNull(gm))
			return JsonFactory.warn("NOT_PART");

		List<Group> groups = new ArrayList<>();

		gm.forEach(y -> groups.add(y.getGroup()));

		return JsonFactory.toJArray(groups);


	}

	@GetMapping("/checkUser")
	private String checkUser(@RequestParam(name = "user") String user) throws  Exception{
		User u = uRepo.findFirstByUser(user);
		if(isNull(u))
			return JsonFactory.error("NO_USER");

		return JsonFactory.result(u.toJson());
	}

	@GetMapping("/getAllUserByGroup")
	private String getAllUserByGroup(@RequestParam(name = "group") int id) {

		throw new RuntimeException("OK");
	}


}
