package com.example.davidloris_project.LiveData;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.davidloris_project.Entity.SubjectEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectListLiveData extends LiveData<List<SubjectEntity>>
{

    private static final String TAG = "SubjectList";

    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public SubjectListLiveData(DatabaseReference ref) {
        reference = ref;
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
            setValue(toSubjectList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<SubjectEntity> toSubjectList(DataSnapshot snapshot) {
        List<SubjectEntity> clients = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            SubjectEntity entity = childSnapshot.getValue(SubjectEntity.class);
            entity.setIdSubject(childSnapshot.getKey());
            clients.add(entity);
        }
        return clients;
    }
}
