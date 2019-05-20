package com.pmark.ticketingtool.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pmark.ticketingtool.model.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
