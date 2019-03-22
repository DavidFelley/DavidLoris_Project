package com.example.davidloris_project.Local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.davidloris_project.Model.Answer;
import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.Model.User;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Database(entities = {User.class, Subject.class, Answer.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract UserDAO userDAO();

    public abstract SubjectDAO subjectDAO();

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "my_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private SubjectDAO subjectDao;
        private UserDAO userDAO;

        private PopulateDbAsyncTask(MyDatabase db) {
            subjectDao = db.subjectDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DateFormat date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

            subjectDao.insertSubject(new Subject("How to test a database ?", "This is our first try with the room database.", "IT", date.format(Calendar.getInstance().getTime())));
            subjectDao.insertSubject(new Subject("How to test a database 2 ?", "This is our first try with the room database.", "IT", date.format(Calendar.getInstance().getTime())));
            subjectDao.insertSubject(new Subject("How to test a database 3 ?", "This is our first try with the room database.", "IT", date.format(Calendar.getInstance().getTime())));
            subjectDao.insertSubject(new Subject("How to test a database 4 ?", "This is our first try with the room database.", "IT", date.format(Calendar.getInstance().getTime())));

            return null;
        }
    }


}
