package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/elolink/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/addchat", method = RequestMethod.POST)
    public ResponseEntity<String> addChat(@RequestBody ChatDto chatDto, Authentication authentication) {
        return chatService.addChat(chatDto, authentication);
    }

    @RequestMapping(value = "/getconversation/{sender}/{receiver}", method = RequestMethod.GET)
    public Mono<List<ChatDto>> getConversation(@PathVariable final String sender, @PathVariable final String receiver) {
        return chatService.getConversation(sender, receiver);
    }

    @RequestMapping(value = "/deletechat/{sender}/{receiver}/{timestamp}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteChat(@PathVariable final String sender, @PathVariable final String receiver, @PathVariable final String timestamp, Authentication authentication) {
        return chatService.deleteChat(sender, receiver, timestamp, authentication);
    }

    @RequestMapping(value = "/updatechat/{sender}/{receiver}/{timestamp}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateChat(@PathVariable final String sender, @PathVariable final String receiver, @PathVariable final String timestamp, @RequestBody ChatDto chat, Authentication authentication) {
        return chatService.updateChat(sender, receiver, timestamp, chat, authentication);
    }
}