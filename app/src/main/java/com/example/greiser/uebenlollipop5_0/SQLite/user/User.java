package com.example.greiser.uebenlollipop5_0.SQLite.user;

public class User {

    private long id;
    private String username;

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }
}
