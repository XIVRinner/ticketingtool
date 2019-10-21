package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Affected;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AffectedRepository extends CrudRepository<Affected, Integer> {

    Affected findById(int id);

    List<Affected> findAllByChangeId(int change_id);
}
