package com.example.davidloris_project.Repository;

import android.arch.lifecycle.LiveData;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.SubjectEntity;
import com.example.davidloris_project.LiveData.SubjectListLiveData;
import com.example.davidloris_project.LiveData.SubjectLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SubjectRepository {

    private static SubjectRepository instance;

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

    public LiveData<SubjectEntity> getSubjectFromIdCloud(final String idSubject) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("subjects");

        return new SubjectLiveData(reference, idSubject);
    }

    public LiveData<List<SubjectEntity>> getSubjectFromCategoryCloud(final String category) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("subjects");

        return new SubjectListLiveData(reference, category);
    }

    public void insertCloud(final SubjectEntity subject, final AsyncTaskListener callBack) {

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
}
