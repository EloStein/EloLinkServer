package de.spring.elolink_spring.dtos;

import de.spring.elolink_spring.entity.User;

public class UserDto {

    private String uuid;
    private String userName;
    private String timestamp;
    private String password;
    private String publicKey;
    private String profilePicture;
    private String gender;
    private String description;

    public UserDto(){
    }

    public UserDto(String uuid, String userName, String timestamp, String password, String publicKey, String profilePicture, String gender, String description) {
        this.uuid = uuid;
        this.userName = userName;
        this.timestamp = timestamp;
        this.password = password;
        this.publicKey = publicKey;
        this.profilePicture = profilePicture;
        this.gender = gender;
        this.description = description;
    }

    public static UserDto fromUser(User user){
        return new UserDto(
                user.getUuid(),
                user.getUserName(),
                user.getTimestamp(),
                user.getPassword(),
                user.getPublicKey(),
                user.getProfilePicture(),
                user.getGender(),
                user.getDescription()
        );
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
