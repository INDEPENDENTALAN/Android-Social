package com.android.social.entity;

public class ActivityEntity {

    private String username;
    private String follow_username;
    private int accept_username;

    public ActivityEntity() {
    }

    public ActivityEntity(String username, String follow_username, int accept_username) {
        this.username = username;
        this.follow_username = follow_username;
        this.accept_username = accept_username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollow_username() {
        return follow_username;
    }

    public void setFollow_username(String follow_username) {
        this.follow_username = follow_username;
    }

    public int getAccept_username() {
        return accept_username;
    }

    public void setAccept_username(int accept_username) {
        this.accept_username = accept_username;
    }
}
