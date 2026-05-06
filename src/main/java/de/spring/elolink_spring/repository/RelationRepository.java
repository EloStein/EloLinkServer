package de.spring.elolink_spring.repository;

import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepository extends JpaRepository<Relation, Integer> {

    boolean existsById(Long id);
    Relation findBySender(String sender);
    Relation findByReceiver(String receiver);
}
