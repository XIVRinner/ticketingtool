package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Change;
import org.springframework.data.repository.CrudRepository;

public interface ChangeRepository extends CrudRepository<Change, Integer> {
	
	
	
	Change findById(int id);

}
