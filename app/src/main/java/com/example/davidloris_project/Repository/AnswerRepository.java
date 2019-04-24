package com.example.davidloris_project.Repository;

import android.arch.lifecycle.LiveData;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.AnswerEntity;
import com.example.davidloris_project.LiveData.AnswerListLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AnswerRepository {

    private static AnswerRepository instance;

    public static AnswerRepository getInstance() {

        if (instance == null) {
            synchronized (AnswerRepository.class) {
                if (instance == null) {
                    instance = new AnswerRepository();
                }
            }
        }
        return instance;
    }

    public void insertCloud(final AnswerEntity answer, final AsyncTaskListener callback) {

        String id = FirebaseDatabase.getInstance().getReference("answers").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("answers")
                .child(id)
                .setValue(answer, ((databaseError, databaseReference) -> {

                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }

                }));

    }

    public void updateCloud(final AnswerEntity answer, AsyncTaskListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("answers")
                .child(answer.getIdAnswer())
                .updateChildren(answer.toMap(), ((databaseError, databaseReference) ->
                {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                }));


    }

    public void deleteCloud(final String idAnswer, AsyncTaskListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("answers")
                .child(idAnswer)
                .removeValue(((databaseError, databaseReference) ->
                {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                }));
    }

    public LiveData<List<AnswerEntity>> getAnswerFromSubject(final String idSubject) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("answers");

        return new AnswerListLiveData(reference, idSubject);
    }
}
