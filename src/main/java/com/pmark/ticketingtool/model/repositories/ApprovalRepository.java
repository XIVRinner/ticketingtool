package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Approval;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApprovalRepository extends CrudRepository<Approval, Integer> {

    List<Approval> findAllByChangeId(int change_id);
}
