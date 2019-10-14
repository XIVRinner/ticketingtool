package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Approval;
import com.pmark.ticketingtool.model.entity.Change;
import com.pmark.ticketingtool.model.entity.Status;
import com.pmark.ticketingtool.model.repositories.ApprovalRepository;
import com.pmark.ticketingtool.model.repositories.ChangeRepository;
import com.pmark.ticketingtool.model.repositories.StatusRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.requireNonNull;

import javax.inject.Inject;

import static com.pmark.ticketingtool.utility.JsonFactory.ok;

@RestController
@RequestMapping("/private")
public class ApprovalController {

    private static final Logger log = LoggerFactory.getLogger(ApprovalController.class);

    @Inject
    private ApprovalRepository approvalRepository;

    @Inject
    private ChangeRepository changeRepository;

    @Inject
    private StatusRepository statusRepository;


    @PostMapping("/createApproval")
    private String createApprovalFor(@RequestParam(name = "changeid") int changeid,
    @RequestParam(name = "statuscode") int statuscode){

        Change c = changeRepository.findById(changeid);
        Status s = statusRepository.findById(statuscode);

        requireNonNull(c, "Change is not found!");
        requireNonNull(s, "Status code is not found!");

        Approval a = new Approval.Builder()
                .withChange(c)
                .withStatus(s)
                .build();

        approvalRepository.save(a);



        return ok();
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    private String handleException(Exception ex){
        log.error("Error in ApprovalController: ", ex);
        return JsonFactory.error(ex.getMessage());
    }
}
