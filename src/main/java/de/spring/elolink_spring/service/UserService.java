package de.spring.elolink_spring.service;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.dtos.UserDto;
import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.entity.User;
import de.spring.elolink_spring.repository.ChatRepository;
import de.spring.elolink_spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Todo: Verification!!!!!

    @Transactional
    public String addUser(UserDto userDto) {
        User user = User.fromDto(userDto);
        user.setId((long) (null == userRepository.findMaxId() ? 0 : userRepository.findMaxId() + 1));
        userRepository.save(user);
        System.out.println("#Added User '" + userDto.getUserName() + "' with Uuid " + userDto.getUuid());
        return "200";
    }

    public UserDto getUser(String uuid) {
        User user = userRepository.findByUuid(uuid);
        return UserDto.fromUser(user);
    }
}
