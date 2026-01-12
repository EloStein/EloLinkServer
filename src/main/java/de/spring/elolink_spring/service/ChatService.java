package de.spring.elolink_spring.service;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.repository.ChatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    public String addChat(ChatDto chatDto) {
        Chat chat = Chat.fromDto(chatDto);
        chat.setId((long) (null == chatRepository.findMaxId() ? 0 : chatRepository.findMaxId() + 1));
        //Todo: Verification!
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



}
