package com.trzaskom.dto;

import com.trzaskom.jpa.model.Message;

public class MessageDTO {

    private Long id;
    private String message;
    private String fromId;
    private String toId;
    private String time;

    public MessageDTO(Message message){
        this.id = message.getId();
        this.message = message.getContent();
        this.fromId = Long.toString(message.getSender().getId());
        this.toId = Long.toString(message.getReceiver().getId());
        this.time = message.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
