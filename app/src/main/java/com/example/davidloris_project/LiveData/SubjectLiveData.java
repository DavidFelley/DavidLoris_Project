package com.example.davidloris_project.LiveData;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.davidloris_project.Entity.SubjectEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SubjectLiveData extends LiveData<SubjectEntity>
{

    private static final String TAG = "SubjectLiveData";

    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();
    private final String idSubject;

    public SubjectLiveData(DatabaseReference ref, String idSubject) {
        this.reference = ref;
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
            setValue(getSubject(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private SubjectEntity getSubject(DataSnapshot snapshot) {
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            SubjectEntity entity = childSnapshot.getValue(SubjectEntity.class);
            entity.setIdSubject(childSnapshot.getKey());

            if(entity.getId().equals(idSubject))
                return entity;
        }

        return null;
    }
}
