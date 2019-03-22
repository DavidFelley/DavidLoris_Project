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

    public User getUserLogin(String username, String password)
    {

        User user = new getUserLoginAsyncTask(userDao).doInBackground(username, password);


        return user;
    }


    private static class getUserLoginAsyncTask extends AsyncTask<String, Void, User>
    {

        private UserDAO userDAO;


        private getUserLoginAsyncTask(UserDAO userDAO){this.userDAO = userDAO;}


        @Override
        protected User doInBackground(String... strings) {

            User user =  userDAO.getUserLogin(strings.toString(),strings.toString());


            return user;
        }

    }
}
