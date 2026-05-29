package de.spring.elolink_spring.repository;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Integer> {

    boolean existsById(Long id);
    List<Relation> findByUsername(String username);
    @Query("select max(s.id) from Relation s")
    Integer findMaxId();
    boolean existsByUsernameAndFriend(String username, String friend);
}
