package com.example.davidloris_project.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;

import java.util.List;

public class UserRepository {
    private UserDAO userDao;
    private LiveData<List<User>> AllUser;

    public UserRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        userDao = database.userDAO();

    }
}
