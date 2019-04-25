package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Repository.UserRepository;

public class UserVM extends AndroidViewModel {

    private UserRepository repository;

    public UserVM(@NonNull Application application) {
        super(application);
        repository = new UserRepository();
    }

    public void createUser(String email, String pwd, UserEntity user, AsyncTaskListener callback) {
        UserRepository.getInstance().register(email, pwd, user, callback);
    }

    public LiveData<String> getUsername(String uId) {

        return repository.getUsername(uId);
    }
}
