package com.example.einfohukum.login;

public class User {

    private String username, kesatuan;
    private int id;
    public User(int id, String username, String kesatuan) {
        this.id = id;
        this.username = username;
        this.kesatuan = kesatuan;
    }
    public String getUsername() {
        return username;
    }
    public String getKesatuan() {
        return kesatuan;
    }

    public int getId() {
        return id;
    }
}
