package de.spring.elolink_spring.repository;

import de.spring.elolink_spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsById(String id);
    boolean existsByEmail(String email);
    @Query("select max(s.id) from User s")
    Integer findMaxId();
    User findByUuid(String uuid);
    Optional<User> findByUserName(String username);
    User findByEmail(String email);
    void deleteById(String id);
    void deleteByUuid(String uuid);
}
