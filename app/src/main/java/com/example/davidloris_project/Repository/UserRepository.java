package com.example.davidloris_project.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;

public class UserRepository {
    private UserDAO userDao;

    public UserRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        userDao = database.userDAO();

    }


    public User getUserByName(String username, String password)
    {
        return userDao.getUserByName(username, password);
    }


    public void insert(User user){
        new UserRepository.InsertUserAsyncTask(userDao).execute(user);
    }



    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void>
    {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO){this.userDAO = userDAO;}

        @Override
        protected Void doInBackground(User... users) {

            userDAO.insertUser(users[0]);
            return null;
        }
    }





}
