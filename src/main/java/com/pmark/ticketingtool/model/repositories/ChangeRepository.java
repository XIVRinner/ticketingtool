package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.Change;

public interface ChangeRepository extends CrudRepository<Change, Integer> {
	
	
	
	Change findById(int id);

}
