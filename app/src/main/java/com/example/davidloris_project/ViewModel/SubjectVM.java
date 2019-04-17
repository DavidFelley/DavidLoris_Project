package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.example.davidloris_project.CompositeObjects.SubjectWithUserName;
import com.example.davidloris_project.Entity.SubjectEntity;
import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.OnAsyncEventListener;
import com.example.davidloris_project.Repository.SubjectRepository;

import java.util.List;

public class SubjectVM extends AndroidViewModel {

    //View model to get the methode


    private SubjectRepository repository;


   public void insertCloud(SubjectEntity subject, OnAsyncEventListener callBack)
   {
       SubjectRepository.getInstance().insetCloud(subject, callBack);
   }



    public SubjectVM(@NonNull Application application) {
        super(application);
        repository = new SubjectRepository(application);
    }

    public LiveData<List<Subject>> getAllSubjectsFromCategory(String category){
        return repository.getAllSubjectsFromCategory(category);
    }

    public LiveData<SubjectWithUserName> getSubjectById(int id){
        return repository.getSubjectById(id);
    }

    public void insert (Subject subject){
        repository.insert(subject);
    }

}
