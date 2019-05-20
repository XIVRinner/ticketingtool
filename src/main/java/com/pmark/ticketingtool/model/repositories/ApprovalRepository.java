package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.Approval;

public interface ApprovalRepository extends CrudRepository<Approval, Integer> {

}
