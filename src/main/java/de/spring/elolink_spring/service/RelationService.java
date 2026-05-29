package de.spring.elolink_spring.service;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.dtos.RelationDto;
import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.entity.Relation;
import de.spring.elolink_spring.entity.User;
import de.spring.elolink_spring.repository.RelationRepository;
import de.spring.elolink_spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class RelationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RelationRepository relationRepository;

    @Transactional
    public ResponseEntity<String> addFriend(RelationDto relationDto, Authentication authentication) {
        if (!userRepository.existsByUserName(relationDto.getFriend())){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }

        if (!Objects.equals(relationDto.getUsername(), authentication.getName())){
            return new ResponseEntity<>("Requested username and authenticated username don't match!", HttpStatus.UNAUTHORIZED);
        }

        if (relationRepository.existsByUsernameAndFriend(relationDto.getUsername(), relationDto.getFriend())){
            return new ResponseEntity<String>("Friendship already exists!", HttpStatus.BAD_REQUEST);
        }

        Relation relation = Relation.fromDto(relationDto);
        relation.setId((long) (null == relationRepository.findMaxId() ? 0 : relationRepository.findMaxId() + 1));
        relation.setTimestamp(LocalDateTime.now().toString());
        relationRepository.save(relation);
        System.out.println("#Added Friend '" + relation.getFriend() + "' for requester " + relation.getUsername());
        return new ResponseEntity<String>("Added fried " + relation.getFriend(), HttpStatus.OK);
    }

    public List<RelationDto> getFriends(Authentication authentication) {
        List<Relation> relations = relationRepository.findByUsername(authentication.getName());
        return relations.stream()
                .sorted(Comparator.comparing(Relation::getTimestamp)) //.reversed()
                .map(RelationDto::fromRelation)
                .toList();
    }
}
