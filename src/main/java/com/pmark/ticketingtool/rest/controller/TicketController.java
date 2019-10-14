package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.*;
import com.pmark.ticketingtool.model.repositories.*;
import com.pmark.ticketingtool.utility.FrontendException;
import com.pmark.ticketingtool.utility.JsonFactory;
import com.pmark.ticketingtool.utility.TicketingException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Calendar;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/private")

public class TicketController {
    
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Inject
    UsersRepository usersRepository;

    @Inject
    GroupRepository groupRepository;

    @Inject
    SeverityRepository severityRepository;

    @Inject
    StatusRepository statusRepository;

    @Inject
    TicketRepository ticketRepository;



    @PostMapping("/createTicket")
    private String createTicket(
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

        if(!st.isTicket())
            throw new TicketingException(String.format("Status given to this change is not appropriate for changes"));

        Timestamp created = new Timestamp(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(created);
        c.add(Calendar.DAY_OF_MONTH, 14);

        Ticket ch = new Ticket.Builder()
                .withCreated(created)
                .withGroup(g)
                .withLongDescription(plain.getString("longDescription"))
                .withShortDescription(plain.getString("shortDescription"))
                .withResponsible(u)
                .withSeverity(s)
                .withStatus(st)
                .build();

        ticketRepository.save(ch);

        return JsonFactory.ok();
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
