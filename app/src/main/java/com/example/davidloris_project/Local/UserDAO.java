package com.example.davidloris_project.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.Model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM tabUsers WHERE username=:username")
    User getUserByName(String username);

    @Query("SELECT username FROM tabUsers WHERE username=:username")
    String getUsername(String username);

    @Query("SELECT password FROM tabUsers WHERE username=:username AND password= :password")
    String getUserPass(String username, String password);

    @Insert
    void insertUser(User user);
}
