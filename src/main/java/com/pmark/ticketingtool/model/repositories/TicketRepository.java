package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
