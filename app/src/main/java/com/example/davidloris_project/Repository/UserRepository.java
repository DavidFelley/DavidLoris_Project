package com.example.davidloris_project.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;

public class UserRepository {

    private UserDAO userDao;

    public UserRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        userDao = database.userDAO();
    }

    public User getUserLogin(String username, String password, AsyncTaskListener listener) {
        new UserRepository.getUserLoginAsyncTask(userDao, listener).execute(username, password);
        return null;
    }

    public AsyncTask<String, Void, User> getUserByUsername(String username)
    {
       AsyncTask<String, Void, User> user = new getUserByUsername(userDao).execute(username);
        return user;
    }




    public void insert(User user) {
        new UserRepository.InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(String username, String password) {
        new UserRepository.updateUserPasswd(userDao).execute(username, password);
    }

    private static class updateUserPasswd extends AsyncTask<String, Void, Void>
    {
        private UserDAO userDAO;

        private updateUserPasswd (UserDAO userDAO)
        {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(String... strings) {

            String username = strings[0];
            String password = strings[1];

            userDAO.updatePasswd(username,password);
            return null;
        }
    }

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

    private static class getUserByUsername extends AsyncTask<String, Void, User>
    {
        private UserDAO userDAO;

        private User user;

        private getUserByUsername(UserDAO userDAO)
        {
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
