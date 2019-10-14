package com.pmark.ticketingtool.rest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {



    @GetMapping("/welcome")
    public String welcome(){


        return "Welcome to Jurassic Park!";
    }
}
