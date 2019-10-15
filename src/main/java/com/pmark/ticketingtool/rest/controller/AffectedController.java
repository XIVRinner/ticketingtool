package com.pmark.ticketingtool.rest.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
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
		Change c = null;
		try {
			c = cRepo.findById(change_id);

		}
		catch (InvalidDataAccessResourceUsageException ex){
			log.error("InvalidDataAccessResourceUsageException was raised: {}", ex);
			return JsonFactory.error(String.format("InvalidDataAccessResourceUsageException was raised: {}", ex.getMessage()));
		}

        if(isNull(c)){
            log.error("Change with CH{} number was not found!", change_id);
            return JsonFactory.error(String.format("Change with CH%d number was not found!", change_id));
        }

		Affected a = new Affected.Builder().
				withChange(c).
				withObjectName(object).
				build();

		log.info("Affected object created with name '{}' with change: CH{}", a.getObjectName(), a.getChange().getId());


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
