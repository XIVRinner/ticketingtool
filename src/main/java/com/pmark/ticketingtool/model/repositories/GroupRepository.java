package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Integer> {

    Group findById(int id);

}
