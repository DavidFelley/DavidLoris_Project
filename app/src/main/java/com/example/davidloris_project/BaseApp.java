package com.example.davidloris_project;

import android.app.Application;

import com.example.davidloris_project.Repository.UserRepository;

public class BaseApp extends Application {

    public UserRepository getClientRepository() {
        return UserRepository.getInstance();
    }

}
