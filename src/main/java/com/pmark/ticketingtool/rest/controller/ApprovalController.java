package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.Approval;
import com.pmark.ticketingtool.model.entity.Change;
import com.pmark.ticketingtool.model.entity.Status;
import com.pmark.ticketingtool.model.repositories.ApprovalRepository;
import com.pmark.ticketingtool.model.repositories.ChangeRepository;
import com.pmark.ticketingtool.model.repositories.StatusRepository;
import com.pmark.ticketingtool.utility.JsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.List;

import static com.pmark.ticketingtool.utility.JsonFactory.ok;
import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/private")
@Slf4j
public class ApprovalController {


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
        if(!s.isChange()){
            log.error("Status code is not represented as a change status code : {}", s.getId());
            return  JsonFactory.error("Status code is not represented as a change status code : " + s.getId());
        }

        log.info("CREATE Approval for Change: CH{}", changeid);

        Approval a = null;
        approvalRepository.save(a);



        return JsonFactory.ok();
    }

    @GetMapping("/getApprovalByChange")
    private String getApprovalByChange(@RequestParam(name = "change_id") int change_id) throws Exception {

        List<Approval> approvalList = approvalRepository.findAllByChangeId(change_id);

        requireNonNull(approvalList);

        log.info("QUERY Approval by Change.Id: {}", change_id);


        return JsonFactory.ok();
    }




    @ResponseBody
    @ExceptionHandler(Exception.class)
    private String handleException(Exception ex){
        log.error("Error in ApprovalController: ", ex);
        return JsonFactory.error(ex.getMessage());
    }
}
