package com.example.davidloris_project.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.Model.User;

import java.util.List;


@Dao
public interface UserDAO {

    @Query("SELECT * FROM tabUsers")
    List<User> getAllUser();

    @Query("SELECT * FROM tabUsers WHERE username=:username AND password=:password ")
    User getUserLogin(String username, String password);

    @Query("SELECT * FROM tabUsers WHERE username=:username")
    LiveData<User> getUserByUsername(String username);

    @Insert
    void insertUser(User user);
}
