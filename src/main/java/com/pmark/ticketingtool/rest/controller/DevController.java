package com.pmark.ticketingtool.rest.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(prefix = "ticektingtool", name = "mode", havingValue = "DEV", matchIfMissing = false)
@RestController
@RequestMapping("/private")
public class DevController {



}
