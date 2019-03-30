package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.Repository.UserRepository;

public class UserVM extends AndroidViewModel {

    //View model to get the methode
    private UserRepository repository;


    public UserVM(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public User getUserLogin(String username, String password, AsyncTaskListener asyncTaskListener) {
        return repository.getUserLogin(username, password, asyncTaskListener);
    }

    public AsyncTask<String, Void, User> getUserByusername(String username) {
        AsyncTask<String, Void, User> user =   repository.getUserByUsername(username);

        return  user;

    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void updateUserPasswd(String username, String password) {
        repository.update(username, password);
    }


}
