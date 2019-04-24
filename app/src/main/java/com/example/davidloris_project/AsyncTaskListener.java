package com.example.davidloris_project;

import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Model.User;

public interface AsyncTaskListener {
    void onFailure(Exception e);
    void onSuccess();
}
