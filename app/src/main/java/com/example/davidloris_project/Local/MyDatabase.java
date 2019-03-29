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

@Database(entities = {User.class, Subject.class, Answer.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract UserDAO userDAO();
    public abstract SubjectDAO subjectDAO();
    public abstract  AnswerDAO answerDAO();

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
        private UserDAO userDao;
        private AnswerDAO answerDao;

        private PopulateDbAsyncTask(MyDatabase db) {
            subjectDao = db.subjectDAO();
            userDao = db.userDAO();
            answerDao = db.answerDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            userDao.insertUser(new User("admin","1234"));

            DateFormat date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            String dateString = date.format(Calendar.getInstance().getTime());

            /* Put some subject in all the categories */
            subjectDao.insertSubject(new Subject("How to test a Actuality ?", "This is our first try with the room database.", "Actuality", dateString,1));
            subjectDao.insertSubject(new Subject("How to test a Actuality 2 ?", "This is our first try with the room database.", "Actuality", dateString,1));

            subjectDao.insertSubject(new Subject("How to test a Automobile ?", "This is our first try with the room database.", "Automobile", dateString,1));

            subjectDao.insertSubject(new Subject("How to test a Game ?", "This is our first try with the room database.", "Game", dateString,1));


            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,1));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,1));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,1));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,1));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,1));

            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,2));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,2));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,2));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,2));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,2));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,2));

            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,3));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,3));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,3));

            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,4));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,4));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,4));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,4));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,4));
            answerDao.insertAnswer(new Answer("This is my text for my answer. I hope it will work fine, because if not... I'm f*cked.", dateString,1,4));

            return null;
        }
    }


}
