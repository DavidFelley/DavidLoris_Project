package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.Repository.UserRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserVM extends AndroidViewModel {

    private UserRepository repository;






    public void createUser(UserEntity user, AsyncTaskListener callback) {
        UserRepository.getInstance().register(user, callback);
    }




    // Local Database USELESS

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
