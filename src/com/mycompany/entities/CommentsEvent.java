/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ahlem
 */
public class CommentsEvent {
    private int id;
    private String content;
    private String email;
     private String nickname; 
     private String created_at;
     private Event idEvent;

    public CommentsEvent(int id, String content, String email, String nickname, String created_at, Event idEvent) {
        this.id = id;
        this.content = content;
        this.email = email;
        this.nickname = nickname;
        this.created_at = created_at;
        this.idEvent = idEvent;
    }

    public CommentsEvent() {
    }

    public CommentsEvent(String content, String email, String nickname, String created_at, Event idEvent) {
        this.content = content;
        this.email = email;
        this.nickname = nickname;
        this.created_at = created_at;
        this.idEvent = idEvent;
    }

    public CommentsEvent(String content, String email, String nickname, String created_at) {
        this.content = content;
        this.email = email;
        this.nickname = nickname;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }
     
}
