package com.pmark.ticketingtool.rest.controller;

import javax.inject.Inject;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmark.ticketingtool.model.entity.Status;
import com.pmark.ticketingtool.model.repositories.StatusReposiotry;
import com.pmark.ticketingtool.utility.JsonFactory;

import java.util.List;


@RestController
public class StatusController {
	
	
	private static final Logger log = LoggerFactory.getLogger(StatusController.class);

	
	@Inject StatusReposiotry sRepo;
	
	
	@GetMapping(value = "/getStatuses")
	public String getMethodName() {
		
		
		List<Status> statuses = (List<Status>) sRepo.findAll();
		
		JSONArray ja = new JSONArray();
		for (Status item : statuses) {
			ja.put(item.toJson());
		}
		
		
		return JsonFactory.result(ja);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	private String handleException(Exception ex) {
		
		log.error(ex.getMessage());
		
		
		return JsonFactory.error(ex.getMessage());
	}
	
	

}
