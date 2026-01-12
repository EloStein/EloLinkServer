package de.spring.elolink_spring.entity;

import de.spring.elolink_spring.dtos.ChatDto;
import de.spring.elolink_spring.dtos.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;
    private String uuid;
    private String userName;
    private String timestamp;
    private String password;
    private String publicKey;
    private String profilePicture;
    private String gender;
    private String description;

    public User(){
    }

    public User(Long id, String uuid, String userName, String timestamp, String password, String publicKey, String profilePicture, String gender, String description) {
        this.id = id;
        this.uuid = uuid;
        this.userName = userName;
        this.timestamp = timestamp;
        this.password = password;
        this.publicKey = publicKey;
        this.profilePicture = profilePicture;
        this.gender = gender;
        this.description = description;
    }

    public static User fromDto(UserDto dto){
        return new User(
                null,
                dto.getUuid(),
                dto.getUserName(),
                dto.getTimestamp(),
                dto.getPassword(),
                dto.getPublicKey(),
                dto.getProfilePicture(),
                dto.getGender(),
                dto.getDescription()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", userName='" + userName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", password='" + password + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
