package de.spring.elolink_spring.dtos;

import de.spring.elolink_spring.entity.Chat;
import de.spring.elolink_spring.service.ChatService;

public class ChatDto {

    private String signature;
    private String sender;
    private String receiver;
    private String message;
    private String aesKeyRec;
    private String aesKeyOwn;
    private String timestamp;

    public ChatDto(){
    }

    public ChatDto(String signature, String sender, String receiver, String message, String aesKeyRec, String aesKeyOwn, String timestamp) {
        this.signature = signature;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.aesKeyRec = aesKeyRec;
        this.aesKeyOwn = aesKeyOwn;
        this.timestamp = timestamp;
    }

    public static ChatDto fromChat(Chat chat){
        return new ChatDto("true",
                chat.getSender(),
                chat.getReceiver(),
                chat.getMessage(),
                chat.getAesKeyRec(),
                chat.getAesKeyOwn(),
                chat.getTimestamp()
        );
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "signature='" + signature + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                ", aesKeyRec='" + aesKeyRec + '\'' +
                ", aesKeyOwn='" + aesKeyOwn + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
