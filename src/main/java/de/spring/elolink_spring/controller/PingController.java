package de.spring.elolink_spring.controller;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.dtos.UserDto;
import de.spring.elolink_spring.entity.User;
import de.spring.elolink_spring.repository.UserRepository;
import de.spring.elolink_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalTime;

@RestController
@RequestMapping("/elolink")
public class PingController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String getPing(){
        return "200\n" + LocalTime.now();
    }

    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    public String debug(){
//        User user = userRepository.findByUuid("1a0cdb45-9e19-4027-9cf3-6b8096b4fe8c");
//        UserDto dto = UserDto.fromUser(user);
//
//        return userService.updateUser("1a0cdb45-9e19-4027-9cf3-6b8096b4fe8c", new UserDto(
//                dto.getUuid(),
//                dto.getUserName(),
//                dto.getEmail(),
//                dto.getVerificationToken(),
//                false,
//                dto.getTimestamp(),
//                dto.getPassword(),
//                dto.getPublicKey(),
//                dto.getProfilePicture(),
//                dto.getGender(),
//                dto.getDescription()
//        ));
        userRepository.deleteById(1);
        return "Deleted User ";
    }

}
