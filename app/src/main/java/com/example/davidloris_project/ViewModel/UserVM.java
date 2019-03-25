package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.Repository.UserRepository;

public class UserVM extends AndroidViewModel {
    private UserRepository repository;


    public UserVM(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public User getUserByName(String username, String password) {
        return repository.getUserLogin(username, password);
    }

    public void insert(User user) {
        repository.insert(user);
    }

}
