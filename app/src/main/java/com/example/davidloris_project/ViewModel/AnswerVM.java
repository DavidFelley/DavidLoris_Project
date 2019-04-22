package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.CompositeObjects.AnswerWithUsername;
import com.example.davidloris_project.Entity.AnswerEntity;
import com.example.davidloris_project.Model.Answer;
import com.example.davidloris_project.Repository.AnswerRepository;

import java.util.List;

public class AnswerVM extends AndroidViewModel {

    //View model to get the methode


    private AnswerRepository repository;


    public void updateCloud(AnswerEntity answerEntity, AsyncTaskListener callback)
    {
        AnswerRepository.getInstance().updateCloud(answerEntity, callback);
    }

    public void deleteCloud(AnswerEntity answerEntity, AsyncTaskListener callback)
    {
        AnswerRepository.getInstance().deleteCloud(answerEntity, callback);
    }

    public void insertCloud(AnswerEntity answerEntity, AsyncTaskListener callback)
    {
        AnswerRepository.getInstance().insertCloud(answerEntity, callback);
    }

    public AnswerVM(@NonNull Application application) {
        super(application);
        repository = new AnswerRepository(application);
    }

    public LiveData<List<AnswerWithUsername>> getAllMessageFromSubject(int idSubject){
        return repository.getAllMessageFromSubject(idSubject);
    }

    public void insert (Answer answer){
        repository.insert(answer);
    }

    public void update (Answer answer){
        repository.update(answer);
    }

    public void delete (int idAnswer) {repository.deleteAnswer(idAnswer);}
}
