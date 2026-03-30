package de.spring.elolink_spring.service;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.repository.ChatRepository;
import de.spring.elolink_spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<String> addChat(ChatDto chatDto, Authentication authentication) {
        if (!userRepository.existsByUserName(chatDto.getSender())){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(chatDto.getSender(), authentication.getName())){
            return new ResponseEntity<>("Requested username and authenticated username don't match!", HttpStatus.UNAUTHORIZED);
        }
        Chat chat = Chat.fromDto(chatDto);
        chat.setId((long) (null == chatRepository.findMaxId() ? 0 : chatRepository.findMaxId() + 1));
        chatRepository.save(chat);
        System.out.println("#Added chat '" + chatDto.getMessage() + "' from sender " + chatDto.getSender());
        return new ResponseEntity<>("Added chat from user " + chat.getSender(), HttpStatus.OK);
    }

    public Mono<List<ChatDto>> getConversation(String uuid1, String uuid2) {
        List<Chat> allChats = new ArrayList<>();
        allChats.addAll(chatRepository.findBySender(uuid1));
        allChats.addAll(chatRepository.findBySender(uuid2));
        List<ChatDto> conversationDto = allChats.stream()
                .filter(chat ->
                        (chat.getSender().equals(uuid1) && chat.getReceiver().equals(uuid2)) ||
                                (chat.getSender().equals(uuid2) && chat.getReceiver().equals(uuid1))
                )
                .sorted(Comparator.comparing(Chat::getTimestamp).reversed())
                .map(ChatDto::fromChat)
                .toList();
        System.out.println();
        return Mono.just(conversationDto);
    }

    @Transactional
    public ResponseEntity<String> deleteChat(String sender, String receiver, String timestamp, Authentication authentication){
        if (!userRepository.existsByUserName(sender)){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(sender, authentication.getName())){
            return new ResponseEntity<>("Requested username and authenticated username don't match!", HttpStatus.UNAUTHORIZED);
        }
        chatRepository.deleteBySenderAndReceiverAndTimestamp(
                sender,
                receiver,
                timestamp);
        System.out.println("Deleted Chat from '"  + sender + "' to '" + receiver + "' from " + timestamp);
        return new ResponseEntity<>("Deleted chat from user " + sender, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateChat(String sender, String receiver, String timestamp, ChatDto chatDto, Authentication authentication){
        if (!userRepository.existsByUserName(sender)){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(sender, authentication.getName())){
            return new ResponseEntity<>("Requested username and authenticated username don't match!", HttpStatus.UNAUTHORIZED);
        }
        List<Chat> chats = chatRepository.findBySenderAndReceiverAndTimestamp(sender, receiver, timestamp);
        chats.forEach(chat -> {
            chat.setSender(chatDto.getSender());
            chat.setReceiver(chatDto.getReceiver());
            chat.setMessage(chatDto.getMessage());
            chat.setAesKeyRec(chatDto.getAesKeyRec());
            chat.setAesKeyOwn(chatDto.getAesKeyOwn());
            chat.setSpec(chatDto.getSpec());
            chat.setTimestamp(chatDto.getTimestamp());
            chatRepository.save(chat);
        });
        System.out.println("Updated Chat from '"  + sender + "' to '" + receiver + "' from " + timestamp);
        return new ResponseEntity<>("Updated chat from user " + sender, HttpStatus.OK);
    }

}
