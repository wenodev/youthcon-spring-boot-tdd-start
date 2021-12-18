package com.youthcon.start.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String phoneNumber;
    private Boolean sent;

    public Review() {
    }

    public Review(Long id, String content, String phoneNumber) {
        this.id = id;
        this.content = content;
        this.phoneNumber = phoneNumber;
    }

    public Review(Long id, String content, String phoneNumber, Boolean isSent) {
        this.id = id;
        this.content = content;
        this.phoneNumber = phoneNumber;
        this.sent = isSent;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getSent() {
        return sent;
    }

    public void makeTrue() {
        this.sent = true;
    }
}
