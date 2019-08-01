package com.pmark.ticketingtool.rest.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmark.ticketingtool.model.entity.Affected;
import com.pmark.ticketingtool.model.entity.Change;
import com.pmark.ticketingtool.model.repositories.AffectedRepository;
import com.pmark.ticketingtool.model.repositories.ChangeRepository;
import com.pmark.ticketingtool.utility.JsonFactory;

@RestController
@RequestMapping("/private")

public class AffectedController {
	
	@Inject AffectedRepository aRepo;
	@Inject ChangeRepository cRepo;
	
	
	@GetMapping(value = "/createAffected")
	public String createAffected(@RequestParam(name="object") String object,
			@RequestParam(name="change_id") int change_id) {
		
		Change c = cRepo.findById(change_id);
		
		Affected a = new Affected();
		a.setChange(c);
		a.setObjectName(object);
		
		aRepo.save(a);
		
		return JsonFactory.ok();
	}


}
