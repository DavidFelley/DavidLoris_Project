package com.example.davidloris_project.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {

    private static UserRepository instance;

    public UserRepository() {
    }

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


    // Local Database USELESS

    private UserDAO userDao;

    public UserRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        userDao = database.userDAO();
    }

    //Get the use log
    public User getUserLogin(String username, String password, AsyncTaskListener listener) {
        new UserRepository.getUserLoginAsyncTask(userDao, listener).execute(username, password);
        return null;
    }

    //Get user by username
    public AsyncTask<String, Void, User> getUserByUsername(String username) {
        AsyncTask<String, Void, User> user = new getUserByUsername(userDao).execute(username);
        return user;
    }

    public void insert(User user) {
        new UserRepository.InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(String username, String password) {
        new UserRepository.updateUserPasswd(userDao).execute(username, password);
    }

    //Update the use in the database
    private static class updateUserPasswd extends AsyncTask<String, Void, Void> {
        private UserDAO userDAO;

        private updateUserPasswd(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(String... strings) {

            String username = strings[0];
            String password = strings[1];

            userDAO.updatePasswd(username, password);
            return null;
        }
    }

    //Insertion of a user
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {

            userDAO.insertUser(users[0]);
            return null;
        }
    }

    private static class getUserByUsername extends AsyncTask<String, Void, User> {
        private UserDAO userDAO;

        private User user;

        private getUserByUsername(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected User doInBackground(String... strings) {
            String username = strings[0];

            user = userDAO.getUserByUsername(username);
            return user;
        }
    }

    private static class getUserLoginAsyncTask extends AsyncTask<String, Void, Void> {
        private UserDAO userDao;
        private AsyncTaskListener listener;
        private User user;

        private getUserLoginAsyncTask(UserDAO userDao, AsyncTaskListener listener) {
            this.userDao = userDao;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(String... infos) {

            String username = infos[0];
            String password = infos[1];

            user = userDao.getUserLogin(username, password);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (user == null) {
                listener.onFailure();
            } else {
                listener.onSuccess();
            }
        }
    }
}
