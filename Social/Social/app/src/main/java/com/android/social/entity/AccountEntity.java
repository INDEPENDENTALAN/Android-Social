package com.android.social.entity;

public class AccountEntity {

    private String username;

    public AccountEntity() {
    }

    public AccountEntity(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
