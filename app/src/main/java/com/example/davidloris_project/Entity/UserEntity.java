package com.example.davidloris_project.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserEntity {

    private String idUser;

    private String username;

    public UserEntity() {
    }

    @Exclude
    public String getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Exclude
    public Map<String, Object> toValueMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        return result;
    }
}