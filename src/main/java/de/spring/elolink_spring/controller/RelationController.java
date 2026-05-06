package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.dtos.RelationDto;
import de.spring.elolink_spring.service.ChatService;
import de.spring.elolink_spring.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elolink/api/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @RequestMapping(value = "/addfriend", method = RequestMethod.POST)
    public ResponseEntity<String> addChat(@RequestBody RelationDto relationDto, Authentication authentication) {
        return relationDto.addChat(relationDto, authentication);
    }

}
