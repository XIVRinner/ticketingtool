package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.Severity;

public interface SeverityRepository extends CrudRepository<Severity, Integer> {

}
