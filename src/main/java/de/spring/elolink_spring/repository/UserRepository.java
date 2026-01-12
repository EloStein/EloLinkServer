package de.spring.elolink_spring.repository;

import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsById(String id);
    @Query("select max(s.id) from Chat s")
    Integer findMaxId();
    User findByUuid(String uuid);
    void deleteById(String id);
    void deleteByUuid(String uuid);
}
