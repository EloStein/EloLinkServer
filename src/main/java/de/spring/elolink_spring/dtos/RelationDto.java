package de.spring.elolink_spring.dtos;

public class RelationDto {

    private String self;
    private String friend;
    private boolean isBlocked;

    public RelationDto(){
    }

    public RelationDto(String self, String friend, boolean isBlocked) {
        this.self = self;
        this.friend = friend;
        this.isBlocked = isBlocked;
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

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
