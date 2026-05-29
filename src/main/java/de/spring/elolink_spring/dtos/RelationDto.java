package de.spring.elolink_spring.dtos;

import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.entity.Relation;
import de.spring.elolink_spring.repository.RelationRepository;

public class RelationDto {

    private String username;
    private String friend;
    private boolean isBlocked;

    public RelationDto(){
    }

    public RelationDto(String username, String friend, boolean isBlocked) {
        this.username = username;
        this.friend = friend;
        this.isBlocked = isBlocked;
    }

    public static RelationDto fromRelation(Relation relation){
        return new RelationDto(
                relation.getUsername(),
                relation.getFriend(),
                relation.isBlocked()
        );
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String self) {
        this.username = self;
    }
}
