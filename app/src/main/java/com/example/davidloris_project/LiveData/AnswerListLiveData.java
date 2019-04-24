package com.example.davidloris_project.LiveData;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.davidloris_project.Entity.AnswerEntity;
import com.example.davidloris_project.Entity.SubjectEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnswerListLiveData extends LiveData<List<AnswerEntity>> {

    private static final String TAG = "SubjectList";

    private final DatabaseReference reference;
    private final String idSubject;
    private final AnswerListLiveData.MyValueEventListener listener = new AnswerListLiveData.MyValueEventListener();

    public AnswerListLiveData(DatabaseReference reference, String idSubject) {
        this.reference = reference;
        this.idSubject = idSubject;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toAnswerList(dataSnapshot, idSubject));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.i(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<AnswerEntity> toAnswerList(DataSnapshot snapshot, String idSubject) {
        List<AnswerEntity> answers = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            AnswerEntity entity = childSnapshot.getValue(AnswerEntity.class);
            entity.setIdAnswer(childSnapshot.getKey());

            if(entity.getIdSubject().equals(idSubject))
                answers.add(entity);

        }
        return answers;
    }
}
