package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.Repository.UserRepository;

import java.util.List;

public class UserVM extends AndroidViewModel
{
    private UserRepository repository;
    private UserDAO userDAO;

    public UserVM (@NonNull Application application)
    {
        super(application);
        repository = new UserRepository(application);

    }

    public User getUserLogin(String username, String password)
    {
        User user = repository.getUserLogin(username, password);


        return user;
    }

    public  void  insert (User user)
    {
        repository.insert(user);
    }

}
