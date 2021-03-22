package com.android.social.entity;

public class PostEntity {

    private String username;
    private String post;
    private String likes;

    public PostEntity() {
    }

    public PostEntity(String username, String post, String likes) {
        this.username = username;
        this.post = post;
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
