package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.Repository.SubjectRepository;

import java.util.List;

public class SubjectVM extends AndroidViewModel {
    private SubjectRepository repository;
    private LiveData<List<Subject>>allSubjects;

    public SubjectVM(@NonNull Application application) {
        super(application);
        repository = new SubjectRepository(application);
        allSubjects = repository.getAllSubjects();
    }
    public LiveData<List<Subject>> getAllSubjects(){
        return allSubjects;
    }

    public void insert (Subject subject){
        repository.insert(subject);
    }


}
