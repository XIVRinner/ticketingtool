package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Change;
import com.pmark.ticketingtool.model.entity.Group;
import com.pmark.ticketingtool.model.entity.Severity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChangeRepository extends CrudRepository<Change, Integer> {
	
	
	
	Change findById(int id);

	List<Change> findAllByGroup(Group group);

	List<Change> findAllByGroupAndSeverity(Group g, Severity s);

}
