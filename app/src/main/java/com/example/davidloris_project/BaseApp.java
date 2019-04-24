package com.example.davidloris_project;

import android.app.Application;

import com.example.davidloris_project.Repository.AnswerRepository;
import com.example.davidloris_project.Repository.SubjectRepository;
import com.example.davidloris_project.Repository.UserRepository;

public class BaseApp extends Application {

    public UserRepository getUserRepository() {
        return UserRepository.getInstance();
    }

    public SubjectRepository getSubjectRepository() {
        return SubjectRepository.getInstance();
    }

    public AnswerRepository getAnswerRepository() {
        return AnswerRepository.getInstance();
    }
}
