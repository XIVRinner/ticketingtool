package com.pmark.ticketingtool.rest.controller;

import com.pmark.ticketingtool.model.entity.*;
import com.pmark.ticketingtool.model.repositories.*;
import com.pmark.ticketingtool.utility.FrontendException;
import com.pmark.ticketingtool.utility.JsonFactory;
import com.pmark.ticketingtool.utility.TicketingException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/private")
@Slf4j
public class TicketController {

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

    @Inject GroupMemberRepository groupMemberRepository;



    @PostMapping("/createTicket")
    private String createTicket(
            @RequestBody String body
    ) throws Exception {

        JSONObject plain = new JSONObject(body);
        User u =  usersRepository.findById(plain.getInt("userid"));
        Group g = groupRepository.findById(plain.getInt("groupid"));
        Severity s = severityRepository.findById(plain.getInt("severity"));
        Status st = statusRepository.findById(plain.getInt("status"));

        requireNonNull(s);
        requireNonNull(g);
        requireNonNull(u);
        requireNonNull(st);

        if(!st.isTicket())
            throw new TicketingException(String.format("Status given to this ticket is not appropriate for tickets!"));

        Timestamp created = new Timestamp(System.currentTimeMillis());

        Ticket ch =
                Ticket.builder()
                .created(created)
                .group(g)
                .longDescription(plain.getString("longDescription"))
                .shortDescription(plain.getString("shortDescription"))
                .responsible(u)
                .severity(s)
                .status(st)
                .build();

        ticketRepository.save(ch);


        return JsonFactory.ok();
    }

    @GetMapping("/getAllTicketsByGroup")
    private String getAllTicketsByGroup(@RequestParam(name = "group") int id) throws Exception {
        Group g = groupRepository.findById(id);

        List<Ticket> tickets = ticketRepository.findAllByGroup(g);

        log.info("QUERY for ALL Tickets for GroupID: {}", id);

        return JsonFactory.toJArray(tickets).toString();
    }

    @GetMapping("/getAllTicketsByGroupSeverity")
    private String getAllTicketsByGroupSeverity(@RequestParam(name = "group") int id, @RequestParam(name = "severity") int sev) throws Exception {
        Group g = groupRepository.findById(id);
        Severity s = severityRepository.findById(sev);
        List<Ticket> tickets = ticketRepository.findAllByGroupAndSeverity(g, s);

        log.info("QUERY for ALL Tickets for GroupID: {} and Severity: {}", id, s);

        return JsonFactory.toJArray(tickets).toString();
    }

    @GetMapping("/getTicketsByUser")
    private String getTicketsByUser(@RequestParam(name = "user") int id) throws Exception {
        User u = usersRepository.findById(id);
        requireNonNull(u);

        List<GroupMember> gm = groupMemberRepository.findAllByUser(u);
        List<Ticket> tickets = new ArrayList<>();

        for (GroupMember y : gm) {
            Group g = y.getGroup();

            tickets.addAll(ticketRepository.findAllByGroup(g));
        }

        JSONArray ja = new JSONArray();

        for (Ticket ticket : tickets) {
            JSONObject jo = new JSONObject();
            jo.put("id", ticket.getId());
            jo.put("created", ticket.getCreated());
            jo.put("severity", ticket.getSeverity().toJson());
            jo.put("responsilbe", ticket.getResponsible().toJson());
            jo.put("short", ticket.getShortDescription());
            ja.put(jo);
        }



        return JsonFactory.result(ja);
    }

    @GetMapping("/getTicketById")
    private String getTicketById(@RequestParam(name = "id") int id) throws Exception{
        Ticket t = ticketRepository.findById(id);

        JSONObject jo = new JSONObject();
        jo.put("group", t.getGroup().toJson());
        jo.put("customer", t.getGroup().getCustomer().toJson());
        jo.put("resolution", t.getResolution());
        jo.put("long", t.getLongDescription());

        return JsonFactory.result(jo);
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
