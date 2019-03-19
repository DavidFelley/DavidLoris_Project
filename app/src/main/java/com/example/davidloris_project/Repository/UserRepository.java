package com.example.davidloris_project.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;

import java.util.List;

public class UserRepository {
    private UserDAO userDao;
    private LiveData<List<User>> AllUser;

    public UserRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        userDao = database.userDAO();

    }


    public void insert (User user)
    {
        //Au moment du sign in
    }

    public  LiveData<List<User>> getAllUser()
    {
        return AllUser;
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
