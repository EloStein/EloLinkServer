package de.spring.elolink_spring.entity;

import de.spring.elolink_spring.dtos.ChatDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Chat {

    @Id
    private Long id;
    private String sender;
    private String receiver;
    private String message;
    private String aesKeyRec;
    private String aesKeyOwn;
    private String spec;
    private String timestamp;

    public Chat(){
    }

    public Chat(Long id, String sender, String receiver, String message, String aesKeyRec, String aesKeyOwn, String spec, String timestamp) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.aesKeyRec = aesKeyRec;
        this.aesKeyOwn = aesKeyOwn;
        this.spec = spec;
        this.timestamp = timestamp;
    }

    public static Chat fromDto(ChatDto dto){
        return new Chat(
                null,
                dto.getSender(),
                dto.getReceiver(),
                dto.getMessage(),
                dto.getAesKeyRec(),
                dto.getAesKeyOwn(),
                dto.getSpec(),
                dto.getTimestamp()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAesKeyRec() {
        return aesKeyRec;
    }

    public void setAesKeyRec(String aesKeyRec) {
        this.aesKeyRec = aesKeyRec;
    }

    public String getAesKeyOwn() {
        return aesKeyOwn;
    }

    public void setAesKeyOwn(String aesKeyOwn) {
        this.aesKeyOwn = aesKeyOwn;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                ", aesKeyRec='" + aesKeyRec + '\'' +
                ", aesKeyOwn='" + aesKeyOwn + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
