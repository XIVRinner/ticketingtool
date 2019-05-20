package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Integer> {

}
