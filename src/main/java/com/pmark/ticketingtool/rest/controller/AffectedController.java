package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Affected;
import com.pmark.ticketingtool.model.entity.Change;
import com.pmark.ticketingtool.model.repositories.AffectedRepository;
import com.pmark.ticketingtool.model.repositories.ChangeRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;


@RestController
@RequestMapping("/private")
@Slf4j
public class AffectedController {


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

		Affected a = null;

		log.info("Affected object created with name '{}' with change: CH{}", a.getObjectName(), a.getChange().get(0).getId());


		aRepo.save(a);
		
		return JsonFactory.ok();
	}
	@GetMapping("/getAffectedById")
	private String getAffectedById(@RequestParam(name="id") int id) throws Exception {
		Affected a = aRepo.findById(id);
		if(isNull(a)) {
			log.error("Affected object is not found with id: {}", id);
			return JsonFactory.error("Affected object is not found with id:" + id);
		}
		log.info("QUERY Affected By ID: {}", id);

		return JsonFactory.result(a.toJson());
	}

	@GetMapping("/getAffectedByChangeId")
	private String getAffectedByChangeId(@RequestParam(name = "change_id") int change_id) throws Exception {
		List<Affected> affectedList = aRepo.findAllByChangeId(change_id);

		requireNonNull(affectedList);
		if(affectedList.size() == 0)
			return JsonFactory.error("There are no affected objects by change: " + change_id);
		log.info("QUERY Affected By Change.ID: {}", change_id);
		return JsonFactory.ok();

	}

	@GetMapping("/getAffectedAll")
	private String getAffectedAll() throws Exception {
		List<Affected> affectedList = (List<Affected>) aRepo.findAll();
		log.info("QUERY Affected [ALL]");
		requireNonNull(affectedList);

		return JsonFactory.ok();
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	private String handleException(Exception ex){
		log.error("Error in AffectedController: ", ex);
		return JsonFactory.error(ex.getMessage());
	}


}
