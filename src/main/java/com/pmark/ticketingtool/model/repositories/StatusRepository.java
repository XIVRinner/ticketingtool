package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {


    Status findById(int id);

}
