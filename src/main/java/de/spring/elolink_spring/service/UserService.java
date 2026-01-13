package de.spring.elolink_spring.service;

import de.spring.elolink_spring.dtos.UserDto;
import de.spring.elolink_spring.entity.User;
import de.spring.elolink_spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Transactional
    public String deleteUser(String uuid){
        userRepository.deleteByUuid(uuid);
        System.out.println("#Deleted User with Uuid " + uuid);
        return "200";
    }

    @Transactional
    public String updateUser(String uuid, UserDto userDto){
        User user = userRepository.findByUuid(uuid);
        user.setUserName(userDto.getUserName());
        user.setDescription(userDto.getDescription());
        user.setGender(userDto.getGender());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setPublicKey(userDto.getPublicKey());
        user.setPassword(userDto.getPassword());
        user.setTimestamp(userDto.getTimestamp());
        userRepository.save(user);
        System.out.println("#Updated User with Uuid " + uuid);
        return "200";
    }
}
