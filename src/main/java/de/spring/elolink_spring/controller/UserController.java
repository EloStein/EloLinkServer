package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.UserDto;
import de.spring.elolink_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elolink/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getself", method = RequestMethod.GET)
    public UserDto getSelf(Authentication authentication){
        return userService.getSelf(authentication);
    }

    // Temporary Method
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserDto user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/getuser/{username}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable final String username){
        return userService.getUser(username);
    }

    @RequestMapping(value = "/deleteuser/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable final String uuid, Authentication authentication){
        return userService.deleteUser(uuid, authentication);
    }

    // Temporary Method
    @RequestMapping(value = "/deleteuserid/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable final String id){
        return userService.deleteUserById(id);
    }

    @RequestMapping(value = "/updateuser/{username}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable final String username, @RequestBody UserDto user, Authentication authentication){
        return userService.updateUser(username, user, authentication);
    }
}
