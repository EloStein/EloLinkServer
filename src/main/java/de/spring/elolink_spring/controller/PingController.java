package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PingController {

    @RequestMapping(value = "/elolink/ping", method = RequestMethod.GET)
    public String addChat(@RequestBody ChatDto chatDto){
        return "200";
    }

}
