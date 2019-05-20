package com.pmark.ticketingtool.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {
	
	
	
	
	
	@GetMapping("/")
	private String root() {
		
		return "Hello World";
	}

}
