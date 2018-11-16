package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;
    private String date;
    private String sentBy;
    private String username;
    private String sentFrom;
    private String sentTo;

    public String getSentFrom() {
        return sentFrom;
    }

    public void setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", username='" + username + '\'' +
                ", sentFrom='" + sentFrom + '\'' +
                ", sentTo='" + sentTo + '\'' +
                '}';
    }
}
