package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.SubjectEntity;
import com.example.davidloris_project.Repository.SubjectRepository;

public class SubjectVM extends AndroidViewModel {

    private SubjectRepository repository;

    public SubjectVM(@NonNull Application application) {
        super(application);
        repository = new SubjectRepository();
    }

    public void insertCloud(SubjectEntity subject, AsyncTaskListener callBack) {
        SubjectRepository.getInstance().insertCloud(subject, callBack);
    }

    public LiveData<SubjectEntity> getSubjectByIdCloud(String idSubject) {
        return repository.getSubjectFromIdCloud(idSubject);
    }
}
