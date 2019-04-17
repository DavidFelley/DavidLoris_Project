package com.example.davidloris_project.Entity;

import com.google.firebase.database.Exclude;

public class UserEntity {

    private String idUser;

    private String username;

    private String password;

    public UserEntity(){}

    @Exclude
    public String getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
