package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Severity;
import com.pmark.ticketingtool.model.repositories.SeverityRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/private")
@Slf4j
public class SeverityController {
	
	@Inject SeverityRepository sRepo;
	
	
	@GetMapping(value = "/getSeverities")
	public String getSeverities() throws Exception {
		
		List<Severity> sevs = (List<Severity>) sRepo.findAll();
		log.info("QUERY for Severities");
		
		
		return JsonFactory.toJArray(sevs);
	}
	
	
	@ExceptionHandler(Exception.class)
	private String handleException(Exception ex) {
		log.error(ex.getMessage());
		
		
		return JsonFactory.error(ex.getMessage());
	}


}
