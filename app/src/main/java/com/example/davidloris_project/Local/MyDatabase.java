package com.example.davidloris_project.Local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.davidloris_project.Model.Answer;
import com.example.davidloris_project.Model.Category;
import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.Model.User;

@Database(entities = {User.class, Subject.class, Category.class, Answer.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract UserDAO userDAO();
    public abstract SubjectDAO subjectDAO();

    public static synchronized MyDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"my_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private SubjectDAO subjectDao;

        private PopulateDbAsyncTask(MyDatabase db){
            subjectDao = db.subjectDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            subjectDao.insertSubject(new Subject());
            return null;
        }
    }


}
