package com.pmark.ticketingtool.rest.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.pmark.ticketingtool.model.entity.Affected;
import com.pmark.ticketingtool.model.entity.Change;
import com.pmark.ticketingtool.model.repositories.AffectedRepository;
import com.pmark.ticketingtool.model.repositories.ChangeRepository;
import com.pmark.ticketingtool.utility.JsonFactory;

import static java.util.Objects.*;


@RestController
@RequestMapping("/private")

public class AffectedController {

	private static final Logger log = LoggerFactory.getLogger(AffectedController.class);
	
	@Inject AffectedRepository aRepo;
	@Inject ChangeRepository cRepo;
	
	
	@PostMapping(value = "/createAffected")
	public String createAffected(@RequestParam(name="object") String object,
			@RequestParam(name="change_id") int change_id) {
		
		Change c = cRepo.findById(change_id);

		requireNonNull(c, String.format("Change with number: CH%d was not found!", change_id));
		
		Affected a = new Affected.Builder().
				withChange(c).
				withObjectName(object).
				build();

		log.info("Affected object created with name '%s' with change: CH%d", a.getObjectName(), a.getObjectName());


		aRepo.save(a);
		
		return JsonFactory.ok();
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	private String handleException(Exception ex){
		log.error("Error in AffectedController: ", ex);
		return JsonFactory.error(ex.getMessage());
	}


}
