package de.spring.elolink_spring.entity;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.dtos.RelationDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.parameters.P;

@Entity
public class Relation {

    @Id
    private Long id;
    private String username;
    private String friend;
    private boolean isAccepted;
    private boolean isBlocked;
    private String timestamp;

    public Relation(){
    }

    public Relation(Long id, String username, String friend, boolean isAccepted, boolean isBlocked, String timestamp) {
        this.id = id;
        this.username = username;
        this.friend = friend;
        this.isAccepted = isAccepted;
        this.isBlocked = isBlocked;
        this.timestamp = timestamp;
    }

    public static Relation fromDto(RelationDto dto){
        return new Relation(
                null,
                dto.getUsername(),
                dto.getFriend(),
                false,
                dto.isBlocked(),
                null
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
