package com.example.davidloris_project.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.sqlite.SQLiteConstraintException;

import com.example.davidloris_project.Model.User;


@Dao
public interface UserDAO {

    //All queries for the database

    @Query("SELECT * FROM tabUsers WHERE username=:username AND password=:password ")
    User getUserLogin(String username, String password);

    @Query("SELECT * FROM tabUsers WHERE username=:username")
    User getUserByUsername(String username);

    @Insert
    void insertUser(User user) throws SQLiteConstraintException;

    @Query("UPDATE tabUsers SET password =:password WHERE username=:username")
    void updatePasswd(String username, String password);
}
