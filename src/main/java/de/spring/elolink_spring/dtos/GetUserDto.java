package de.spring.elolink_spring.dtos;

import de.spring.elolink_spring.entity.User;

public class GetUserDto {

    private String uuid;
    private String userName;
    private boolean isVerified;
    private String timestamp;
    private String publicKey;
    private String profilePicture;
    private String gender;
    private String description;

    public GetUserDto(){
    }

    public GetUserDto(String uuid, String userName, boolean isVerified, String timestamp, String publicKey, String profilePicture, String gender, String description) {
        this.uuid = uuid;
        this.userName = userName;
        this.isVerified = isVerified;
        this.timestamp = timestamp;
        this.publicKey = publicKey;
        this.profilePicture = profilePicture;
        this.gender = gender;
        this.description = description;
    }

    public static GetUserDto fromUser(User user){
        return new GetUserDto(
                user.getUuid(),
                user.getUserName(),
                user.isVerified(),
                user.getTimestamp(),
                user.getPublicKey(),
                user.getProfilePicture(),
                user.getGender(),
                user.getDescription()
        );
    }

    public boolean iIsVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
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
