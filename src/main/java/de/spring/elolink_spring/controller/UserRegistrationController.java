package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.SignupUserDto;
import de.spring.elolink_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/elolink/signup", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody SignupUserDto user){
        return userService.registerUser(new SignupUserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        ));

    }

    @GetMapping("/elolink/signup/verify")
    public ResponseEntity verificationEmail(@RequestParam("token") String token) {
        return userService.verifyUser(token);

    }
}
