package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Status;
import com.pmark.ticketingtool.model.repositories.StatusRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/private")
@Slf4j
public class StatusController {

	
	@Inject
    StatusRepository sRepo;
	
	
	@GetMapping(value = "/getStatuses")
	public String getStatuses() throws Exception {
		
		
		List<Status> statuses = (List<Status>) sRepo.findAll();

		log.info("QUERY for STATUSES");
		
		return JsonFactory.toJArray(statuses);
	}

	@GetMapping(value = "/getTicketStatuses")
	public String getTicketStatuses() throws Exception {


		List<Status> statuses = (List<Status>) sRepo.findAll();

		List<Status> ticketStats = new ArrayList<>();

		log.trace("QUERY for Ticket Statuses");
		statuses.forEach(y -> {
			if(y.isTicket() || y.isFailed())
				ticketStats.add(y);
		});


		return JsonFactory.toJArray(ticketStats);
	}
	@GetMapping(value = "/getChangeStatuses")
	public String getChangeStatuses() throws Exception {


		List<Status> statuses = (List<Status>) sRepo.findAll();
		log.trace("QUERY for Change Statuses");
		List<Status> changeStats = new ArrayList<>();

		statuses.forEach(y -> {
			if(y.isChange() || y.isFailed())
				changeStats.add(y);
		});


		return JsonFactory.toJArray(changeStats);
	}





	@ExceptionHandler(Exception.class)
	private String handleException(Exception ex) {
		
		log.error(ex.getMessage());
		
		
		return JsonFactory.error(ex.getMessage());
	}
	
	

}
