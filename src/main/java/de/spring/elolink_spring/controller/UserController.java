package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.UserDto;
import de.spring.elolink_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/elolink/adduser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserDto user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/elolink/getuser/{username}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable final String username){
        return userService.getUser(username);
    }

    @RequestMapping(value = "/elolink/deleteuser/{uuid}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable final String uuid){
        return userService.deleteUser(uuid);
    }

    @RequestMapping(value = "/elolink/deleteuserid/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable final String id){
        return userService.deleteUserById(id);
    }

    @RequestMapping(value = "/elolink/updateuser/{uuid}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable final String uuid, @RequestBody UserDto user){
        return userService.updateUser(uuid, user);
    }
}
