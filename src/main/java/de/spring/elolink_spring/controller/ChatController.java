package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
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

    @RequestMapping(value = "/elolink/deletechat/{sender}/{receiver}/{timestamp}", method = RequestMethod.DELETE)
    public String deleteChat(@PathVariable final String sender, @PathVariable final String receiver, @PathVariable final String timestamp){
        return chatService.deleteChat(sender, receiver, timestamp);
    }

    @RequestMapping(value = "/elolink/updatechat/{sender}/{receiver}/{timestamp}", method = RequestMethod.PUT)
    public String updateChat(@PathVariable final String sender, @PathVariable final String receiver, @PathVariable final String timestamp, @RequestBody ChatDto chat){
        return chatService.updateChat(sender, receiver, timestamp, chat);
    }

}
