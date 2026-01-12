package de.spring.elolink_spring.service;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.repository.ChatRepository;
import io.netty.handler.codec.dns.DnsResponseCode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    //Todo: Verification!!!!!

    @Transactional
    public String addChat(ChatDto chatDto) {
        Chat chat = Chat.fromDto(chatDto);
        chat.setId((long) (null == chatRepository.findMaxId() ? 0 : chatRepository.findMaxId() + 1));
        chatRepository.save(chat);
        System.out.println("#Added Chat '" + chatDto.getMessage() + "' from Sender " + chatDto.getSender());
        return "200";
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
    public String deleteChat(String sender, String receiver, String timestamp){
        chatRepository.deleteBySenderAndReceiverAndTimestamp(
                sender,
                receiver,
                timestamp);
        System.out.println("Deleted Chat from '"  + sender + "' to '" + receiver + "' from " + timestamp);
        return "200";
    }

    @Transactional
    public String updateChat(String sender, String receiver, String timestamp, ChatDto chatDto){
        List<Chat> chats = chatRepository.findBySenderAndReceiverAndTimestamp(sender, receiver, timestamp);
        chats.forEach(chat -> {
            chat.setSender(chatDto.getSender());
            chat.setReceiver(chatDto.getReceiver());
            chat.setMessage(chatDto.getMessage());
            chat.setAesKeyRec(chatDto.getAesKeyRec());
            chat.setAesKeyOwn(chatDto.getAesKeyOwn());
            chat.setTimestamp(chatDto.getTimestamp());
            chatRepository.save(chat);
        });
        System.out.println("Updated Chat from '"  + sender + "' to '" + receiver + "' from " + timestamp);
        return "200";
    }



}
