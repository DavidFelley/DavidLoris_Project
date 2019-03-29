package com.example.davidloris_project;

import com.example.davidloris_project.Model.User;

public interface AsyncTaskListener {
    void onFailure();
    void onSuccess(User user);
}
