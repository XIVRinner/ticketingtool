package com.pmark.ticketingtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.pmark.ticketingtool")
public class TicketingTool {
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TicketingTool.class, args);
	}


}
