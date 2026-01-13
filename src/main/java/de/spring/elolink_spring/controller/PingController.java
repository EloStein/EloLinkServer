package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class PingController {

    @RequestMapping(value = "/elolink/ping", method = RequestMethod.GET)
    public String getPing(){
        return "200\n" + LocalTime.now();
    }

}
