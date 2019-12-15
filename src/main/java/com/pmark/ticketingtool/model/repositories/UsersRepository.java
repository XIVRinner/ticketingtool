package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Integer> {
	
	
	User findFirstByUserAndPass(String user, String pass);
	
	User findById(int id);

    User findFirstByUser(String user);

}
