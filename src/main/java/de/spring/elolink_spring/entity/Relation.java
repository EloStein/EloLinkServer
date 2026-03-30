package de.spring.elolink_spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Relation {

    @Id
    private Long id;
    private String self;
    private String friend;
    private boolean isAccepted;
    private boolean isBlocked;
    private String timestamp;

}
