package com.example.davidloris_project.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tabUsers")
public class User {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idUser;

    private String username;

    private String password;

    public  User ()
    {

    }

    public User (String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


}
