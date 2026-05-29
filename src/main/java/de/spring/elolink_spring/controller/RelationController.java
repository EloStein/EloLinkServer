package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.dtos.RelationDto;
import de.spring.elolink_spring.entity.Relation;
import de.spring.elolink_spring.service.ChatService;
import de.spring.elolink_spring.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/elolink/api/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @RequestMapping(value = "/addfriend", method = RequestMethod.POST)
    public ResponseEntity<String> addFriend(@RequestBody RelationDto relationDto, Authentication authentication) {
        return relationService.addFriend(relationDto, authentication);
    }

    @RequestMapping(value = "/getfriend", method = RequestMethod.GET)
    public List<RelationDto> getFriends(Authentication authentication) {
        return relationService.getFriends(authentication);
    }

//    @RequestMapping(value = "/removefriend", method = RequestMethod.GET)
//    public List<RelationDto> removeFriend(Authentication authentication) {
//    }
//
//    @RequestMapping(value = "/blockfriend", method = RequestMethod.GET)
//    public List<RelationDto> removeFriend(Authentication authentication) {
//    }
//
//    @RequestMapping(value = "/acceptfriend", method = RequestMethod.GET)
//    public List<RelationDto> removeFriend(Authentication authentication) {
//
//    }

}
