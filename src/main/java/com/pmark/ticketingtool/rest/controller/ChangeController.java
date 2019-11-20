package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.*;
import com.pmark.ticketingtool.model.repositories.*;
import com.pmark.ticketingtool.utility.FrontendException;
import com.pmark.ticketingtool.utility.JsonFactory;
import com.pmark.ticketingtool.utility.TicketingException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static java.util.Objects.requireNonNull;


@RestController
@RequestMapping("/private")
@Slf4j
public class ChangeController {


    @Inject
    UsersRepository usersRepository;

    @Inject
    SeverityRepository severityRepository;

    @Inject
    GroupRepository groupRepository;

    @Inject
    StatusRepository statusRepository;

    @Inject
    ChangeRepository changeRepository;

    /**
     * Creates a change from a JSON Object
     *
     * @param body JSON Object from frontend with a REQUEST = {JsonObject}
     * @return
     *
     * @throws FrontendException
     */
    @PostMapping("/createChange")
    private String createChange(
        @RequestBody String body
    ) throws FrontendException, TicketingException {

        JSONObject jo = new JSONObject(body);
        if(jo.has("ERROR"))
            throw new FrontendException(jo.get("ERROR").toString());

        JSONObject plain = jo.getJSONObject("REQUEST");

        User u =  usersRepository.findById(plain.getInt("userid"));
        Group g = groupRepository.findById(plain.getInt("groupid"));
        Severity s = severityRepository.findById(plain.getInt("severity"));
        Status st = statusRepository.findById(plain.getInt("status"));

        requireNonNull(s);
        requireNonNull(g);
        requireNonNull(u);
        requireNonNull(st);

        if(!st.isChange())
            throw new TicketingException(String.format("Status given to this change is not appropriate for changes"));

        Timestamp created = new Timestamp(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(created);
        c.add(Calendar.DAY_OF_MONTH, 14);

        Timestamp deadline = new Timestamp(c.getTimeInMillis());

        Change ch = Change.builder()
                .created(created)
                .group(g)
                .longDescription(plain.getString("longDescription"))
                .shortDescription(plain.getString("shortDescription"))
                .responsible(u)
                .severity(s)
                .status(st)
                .deadline((plain.has("deadline") ? new Timestamp(plain.getLong("deadline")) : deadline))
                .build();


        changeRepository.save(ch);

        return JsonFactory.ok();
    }

    @GetMapping("/getChangeByGroup")
    private String getChangeByGroup(@RequestParam(name = "id") int id) throws Exception {
        Group g = groupRepository.findById(id);

        requireNonNull(g, "This group does not exists");


        List<Change> changeList = changeRepository.findAllByGroup(g);

        log.info("QUERY for Change(s) with group ID: {}", id);

        return JsonFactory.toJArray(changeList).toString();
    }

    @GetMapping("/getChangeByGroupAndSeverity")
    private String getChangeByGroupAndSeverity(@RequestParam(name = "id") int id, @RequestParam(name = "sevid") int sev) throws Exception {
        Group g = groupRepository.findById(id);
        Severity s = severityRepository.findById(sev);

        requireNonNull(s, "This severity doesn't exists");
        requireNonNull(g, "This group does not exists");



        List<Change> changeList = changeRepository.findAllByGroupAndSeverity(g, s);

        log.info("QUERY for Change(s) with Severity ({}) and group ID: {}", s.getName(),id);

        return JsonFactory.toJArray(changeList).toString();
    }


    @ResponseBody
    @ExceptionHandler(FrontendException.class)
    private String handleFrontendException(FrontendException ex){

        log.error("Frontend Exception occurred in ChangeController", ex);

        return JsonFactory.error(ex.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(TicketingException.class)
    private String handleFrontendException(TicketingException ex){

        log.error("Ticketing Exception occurred in ChangeController", ex);

        return JsonFactory.error(ex.getMessage());
    }

}
