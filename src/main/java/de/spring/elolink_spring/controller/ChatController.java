package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/elolink/addchat", method = RequestMethod.POST)
    public String addChat(@RequestBody ChatDto chatDto){
        return chatService.addChat(chatDto);
    }

    @RequestMapping(value = "/elolink/getconversation/{uuid1}/{uuid2}", method = RequestMethod.GET)
    public Mono<List<ChatDto>> getConversation(@PathVariable final String uuid1, @PathVariable final String uuid2){
        return chatService.getConversation(uuid1, uuid2);
    }

}
