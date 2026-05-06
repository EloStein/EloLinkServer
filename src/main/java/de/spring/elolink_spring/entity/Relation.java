package de.spring.elolink_spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.parameters.P;

@Entity
public class Relation {

    @Id
    private Long id;
    private String self;
    private String friend;
    private boolean isAccepted;
    private boolean isBlocked;
    private String timestamp;

    public Relation(){
    }

    public Relation(Long id, String self, String friend, boolean isAccepted, boolean isBlocked, String timestamp) {
        this.id = id;
        this.self = self;
        this.friend = friend;
        this.isAccepted = isAccepted;
        this.isBlocked = isBlocked;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
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
