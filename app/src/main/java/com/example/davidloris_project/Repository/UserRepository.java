package com.example.davidloris_project.Repository;

import android.arch.lifecycle.LiveData;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.LiveData.UserLiveData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {

    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public void signIn(final String email, final String password,
                       final OnCompleteListener<AuthResult> listener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);
    }

    public void register(final String email, final String pwd, final UserEntity user, final AsyncTaskListener callback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        user.setIdUser(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        insert(user, callback);
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    private void insert(final UserEntity user, final AsyncTaskListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user.toValueMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                        FirebaseAuth.getInstance().getCurrentUser().delete()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        callback.onFailure(null);
                                    } else {
                                        callback.onFailure(task.getException());
                                    }
                                });
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public LiveData<String> getUsername(String uId){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        return new UserLiveData(reference,uId);
    }
}
