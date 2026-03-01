package de.spring.elolink_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/elolink/login")
    public String login() {
        return "login";
    }

    @GetMapping("/elolink/signup")
    public String signup() {
      return "signup";
    }

    @GetMapping("/elolink/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/elolink/index")
    public String index() {
        return "index";
    }
}
