package com.example.davidloris_project.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;

import java.util.List;

public class UserRepository {

    private UserDAO userDao;
    private static User myUser;

    public UserRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        userDao = database.userDAO();
    }

    public User getUserLogin(String username, String password){

        new UserRepository.getUserLoginAsyncTask(userDao).execute(username,password);

        return myUser;
    }

    public void insert(User user) {
        new UserRepository.InsertUserAsyncTask(userDao).execute(user);
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

    private static class getUserLoginAsyncTask extends AsyncTask<String, Void, User> {
        private UserDAO userDao;

        private getUserLoginAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... infos) {

            String username = infos[0];
            String password = infos[1];

            User user = userDao.getUserLogin(username, password);

            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            myUser = user;
        }
    }


}
