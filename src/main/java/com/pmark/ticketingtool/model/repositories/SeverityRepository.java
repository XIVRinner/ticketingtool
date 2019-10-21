package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Severity;
import org.springframework.data.repository.CrudRepository;

public interface SeverityRepository extends CrudRepository<Severity, Integer> {

    Severity findById(int id);

}
