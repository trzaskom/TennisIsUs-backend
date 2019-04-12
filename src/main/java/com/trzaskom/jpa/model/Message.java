package com.trzaskom.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Messages")
public class Message extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private User sender;

    @OneToOne(fetch = FetchType.EAGER)
    private User receiver;

    @NotNull
    private String content;

    @NotNull
    @Size(max = 8)
    private String time;

    private Integer consumed;

    public Message() {}

    public Message(User sender, User receiver, @NotNull String content, @NotNull @Size(max = 8) String time, Integer consumed) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.time = time;
        this.consumed = consumed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getConsumed() {
        return consumed;
    }

    public void setConsumed(Integer consumed) {
        this.consumed = consumed;
    }
}
