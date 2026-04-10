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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
         Optional<User> user = Optional.ofNullable(userRepository.findByUserName(username));

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
        User existingUser = userRepository.findByUserName(user.getUserName());

        if (existingUser != null) {
            if (existingUser.isVerified()) {
                return new ResponseEntity<>("User existing and already verified!", HttpStatus.BAD_REQUEST);
            } else {
                String mail = user.getEmail();
                String existingMail = existingUser.getEmail();
                if (Objects.equals(mail, existingMail)) {
                    String verificationToken = JwtTokenUtil.generateToken(existingUser.getEmail());
                    existingUser.setVerificationToken(verificationToken);
                    userRepository.save(existingUser);

                    emailService.sendVerificationEmail(existingUser.getEmail(), verificationToken);
                    System.out.println("Verification Email resent. Check your Inbox");
                    return new ResponseEntity<>("Verification Email resent. Check your Inbox", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Username already in use!", HttpStatus.OK);
                }
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String verificationToken = JwtTokenUtil.generateToken(user.getEmail());
        user.setVerificationToken(verificationToken);
        //user.setId((long) (null == userRepository.findMaxId() ? 0 : userRepository.findMaxId() + 1));
        user.setId(null);
        userRepository.save(user);

        emailService.sendVerificationEmail(user.getEmail(), verificationToken);
        return new ResponseEntity<>("Registration successful! Please verify your via Email", HttpStatus.OK);
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
            if (existingUser.isVerified()) {
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
        User user = userRepository.findByUserName(username); //Changed to User instead of Optional<User> and removed .get from fromUser(); :COULD CAUSE CRASHED
        return UserDto.fromUser(user);
    }

    public UserDto getSelf(Authentication authentication) {
        User user =  userRepository.findByUserName(authentication.getName());  //Changed to User instead of Optional<User> and removed .get from fromUser(); :COULD CAUSE CRASHED
        return UserDto.fromUser(user);
    }

    @Transactional
    public ResponseEntity<String> deleteUser(String username, Authentication authentication){
        if (!userRepository.existsByUserName(username)){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(username, authentication.getName())){
            return new ResponseEntity<>("Requested username and authenticated username don't match!", HttpStatus.UNAUTHORIZED);
        }
        userRepository.deleteByUserName(username);
        System.out.println("#Deleted User with Uuid " + username);
        return new ResponseEntity<>("Deleted User " + username, HttpStatus.OK);
    }

    @Transactional
    public String deleteUserById(String id){
        userRepository.deleteById(id);
        System.out.println("#Deleted User with Id " + id);
        return "200";
    }

    @Transactional
    public ResponseEntity<String> updateUser(String username, UserDto userDto, Authentication authentication){
        if (!userRepository.existsByUserName(username)){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(username, authentication.getName())){
            return new ResponseEntity<>("Requested username and authenticated username don't match!", HttpStatus.UNAUTHORIZED);
        }
        User user = userRepository.findByUserName(username);  //Changed to User instead of Optional<User> and removed .get from fromUser(); :COULD CAUSE CRASHED
        User updatedUser = user;
        updatedUser.setUserName(userDto.getUserName());
        updatedUser.setDescription(userDto.getDescription());
        updatedUser.setGender(userDto.getGender());
        updatedUser.setProfilePicture(userDto.getProfilePicture());
        updatedUser.setPublicKey(userDto.getPublicKey());
        updatedUser.setPassword(userDto.getPassword());
        updatedUser.setTimestamp(userDto.getTimestamp());
        userRepository.save(updatedUser);
        System.out.println("#Updated User with Uuid " + updatedUser);
        return new ResponseEntity<>("Updated User " + username, HttpStatus.OK);
    }



}
