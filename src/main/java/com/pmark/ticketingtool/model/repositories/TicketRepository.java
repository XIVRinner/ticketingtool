package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Group;
import com.pmark.ticketingtool.model.entity.Severity;
import com.pmark.ticketingtool.model.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {


    List<Ticket> findAllByGroup(Group id);

    Ticket findById(int id);

    List<Ticket> findAllByGroupAndSeverity(Group g, Severity s);



}
