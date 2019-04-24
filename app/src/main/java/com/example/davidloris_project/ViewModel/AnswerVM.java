package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.AnswerEntity;
import com.example.davidloris_project.Repository.AnswerRepository;

public class AnswerVM extends AndroidViewModel {

    //View model to get the methode

    private AnswerRepository repository;

    public AnswerVM(@NonNull Application application) {
        super(application);
        repository = new AnswerRepository();
    }

    public void updateCloud(AnswerEntity answerEntity, AsyncTaskListener callback)
    {
        AnswerRepository.getInstance().updateCloud(answerEntity, callback);
    }

    public void deleteCloud(String idAnswer, AsyncTaskListener callback)
    {
        AnswerRepository.getInstance().deleteCloud(idAnswer, callback);
    }

    public void insertCloud(AnswerEntity answerEntity, AsyncTaskListener callback)
    {
        AnswerRepository.getInstance().insertCloud(answerEntity, callback);
    }
}
