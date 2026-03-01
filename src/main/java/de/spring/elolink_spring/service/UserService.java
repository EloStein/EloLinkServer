package de.spring.elolink_spring.service;

import de.spring.elolink_spring.dtos.SignupUserDto;
import de.spring.elolink_spring.dtos.UserDto;
import de.spring.elolink_spring.entity.User;
import de.spring.elolink_spring.repository.UserRepository;
import de.spring.elolink_spring.utils.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user = userRepository.findByUserName(username);
         if (user.isPresent()) {
             var userObject = user.get();
             return org.springframework.security.core.userdetails.User.builder()
                     .username(userObject.getUserName())
                     .password(userObject.getPassword())
                     .build();
         } else {
             throw new UsernameNotFoundException(username);
         }
    }

    @Transactional
    public ResponseEntity<String> registerUser(SignupUserDto userDto){
        User user = User.fromSignupDto(userDto);
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            if (existingUser.isVerified()) {
                return new ResponseEntity<>("User existing and already verified!", HttpStatus.BAD_REQUEST);
            } else {
                String verificationToken = JwtTokenUtil.generateToken(existingUser.getEmail());
                existingUser.setVerificationToken(verificationToken);
                userRepository.save(existingUser);

                emailService.sendVerificationEmail(existingUser.getEmail(), verificationToken);
                return new ResponseEntity<>("Verification Email resent. Check your Inbox", HttpStatus.OK);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String verificationToken = JwtTokenUtil.generateToken(user.getEmail());
        user.setVerificationToken(verificationToken);
        user.setId((long) (null == userRepository.findMaxId() ? 0 : userRepository.findMaxId() + 1));
        userRepository.save(user);

        emailService.sendVerificationEmail(user.getEmail(), verificationToken);
        return new ResponseEntity<>("Registration succesfull! Please verify your Email", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity verifyUser(String token) {
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email);
        if (user == null || user.getVerificationToken() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token Expired!");
        } else if (!jwtUtil.validateToken(token) || !user.getVerificationToken().equals(token)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token Expired!");
        }

        user.setVerificationToken(null);
        user.setVerified(true);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Email successfully verified!");

    }

    @Transactional
    public ResponseEntity<String> addUser(UserDto userDto) {
        User user = User.fromDto(userDto);
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null){
            if (existingUser.isVerified()){
                return new ResponseEntity<>("User already existing and verified", HttpStatus.BAD_REQUEST);
            } else {
                String verificationToken = "123123";
                existingUser.setVerificationToken(verificationToken);
                userRepository.save(existingUser);
                //Todo: Send Email Code
                return new ResponseEntity<>("Verification Email resent. Check your Inbox", HttpStatus.OK);
            }

        }

        user.setId((long) (null == userRepository.findMaxId() ? 0 : userRepository.findMaxId() + 1));
        System.out.println("# " + user.getId());
        userRepository.save(user);
        System.out.println("#Added User '" + userDto.getUserName() + "' with Uuid " + userDto.getUuid());
        return new ResponseEntity<>("200",HttpStatus.OK);
    }

    public UserDto getUser(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return UserDto.fromUser(user.get());
    }

    @Transactional
    public String deleteUser(String uuid){
        userRepository.deleteByUuid(uuid);
        System.out.println("#Deleted User with Uuid " + uuid);
        return "200";
    }

    @Transactional
    public String deleteUserById(String id){
        userRepository.deleteById(id);
        System.out.println("#Deleted User with Id " + id);
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
