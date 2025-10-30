package com.example.sprite.Models;

public class User {
    private String name;
    private String userRole;

    public User(String name, String userRole) {
        this.name = name;
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
