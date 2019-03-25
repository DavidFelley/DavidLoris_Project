package com.example.davidloris_project.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.Model.User;


@Dao
public interface UserDAO {

    @Query("SELECT * FROM tabUsers WHERE username=:username AND password=:password ")
    User getUserByName(String username, String password);



    @Insert
    void insertUser(User user);
}
