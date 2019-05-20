package com.pmark.ticketingtool.rest.controller;

import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmark.ticketingtool.model.entity.Severity;
import com.pmark.ticketingtool.model.repositories.SeverityRepository;
import com.pmark.ticketingtool.utility.JsonFactory;

@RestController
public class SeverityController {
	
	@Inject SeverityRepository sRepo;
	
	
	private static final Logger log = LoggerFactory.getLogger(SeverityController.class);

	
	
	@GetMapping(value = "/getSeverities")
	public String getMethodName() {
		
		List<Severity> sevs = (List<Severity>) sRepo.findAll();
		
		JSONArray ja = new JSONArray();
		for (Severity item : sevs) {
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
