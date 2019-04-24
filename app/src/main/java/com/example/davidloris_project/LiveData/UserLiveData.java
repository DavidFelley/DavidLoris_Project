package com.example.davidloris_project.LiveData;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.davidloris_project.Entity.UserEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserLiveData extends LiveData<String> {
    private static final String TAG = "StudentListLiveData";

    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();
    private final String uId;

    public UserLiveData(DatabaseReference ref, String uId) {
        reference = ref;
        this.uId = uId;
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
            setValue(getUsername(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private String getUsername(DataSnapshot snapshot) {
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            UserEntity entity = childSnapshot.getValue(UserEntity.class);
            entity.setIdUser(childSnapshot.getKey());
            Log.i("*** USER ID ***", entity.getIdUser());

            if(entity.getIdUser().equals(uId))
                return entity.getUsername();
        }

        return "";
    }
}
