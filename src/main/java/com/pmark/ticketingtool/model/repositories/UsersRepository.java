package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.User;

public interface UsersRepository extends CrudRepository<User, Integer> {
	
	
	User findFirstByUserAndPass(String user, String pass);
	
	User findById(int id);

}
