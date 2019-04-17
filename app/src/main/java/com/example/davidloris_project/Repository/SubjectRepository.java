package com.example.davidloris_project.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.davidloris_project.CompositeObjects.SubjectWithUserName;
import com.example.davidloris_project.Entity.SubjectEntity;
import com.example.davidloris_project.LiveData.SubjectListLiveData;
import com.example.davidloris_project.LiveData.SubjectLiveData;
import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.SubjectDAO;
import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SubjectRepository {
    private SubjectDAO subjectDao;

    private static SubjectRepository instance;

    public SubjectRepository() {

    }

    public static SubjectRepository getInstance() {
        if (instance == null) {
            synchronized (SubjectRepository.class) {
                if (instance == null) {
                    instance = new SubjectRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<SubjectEntity> getSubjectCloud(final String idSubject)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("subjects")
                .child(idSubject);

        return new SubjectLiveData(reference);
    }


    public LiveData<List<SubjectEntity>> getAllSubjectsCloud()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("subjects");
        return new SubjectListLiveData(reference);
    }

    public void insetCloud(final SubjectEntity subject, final OnAsyncEventListener callBack)
    {

        String id = FirebaseDatabase.getInstance().getReference("subjects").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("subjects")
                .child(id)
                .setValue(subject, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callBack.onFailure(databaseError.toException());
                    } else {
                        callBack.onSuccess();
                    }
                });
    }


    public SubjectRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        subjectDao = database.subjectDAO();
    }

    //Get all subjects from category
    public LiveData<List<Subject>> getAllSubjectsFromCategory(String category){
        return subjectDao.getAllSubjectsFromCategory(category);
    }

    //Get all subjects by id
    public LiveData<SubjectWithUserName> getSubjectById(int id){
        return subjectDao.getSubjectById(id);
    }

    //Insertion subject
    public void insert(Subject subject){
        new InsertSubjectAsyncTask(subjectDao).execute(subject);
    }

    private static class InsertSubjectAsyncTask extends AsyncTask<Subject, Void, Void>{

        private SubjectDAO subjectDao;

        private InsertSubjectAsyncTask(SubjectDAO subjectDao){
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.insertSubject(subjects[0]);
            return null;
        }
    }




}
