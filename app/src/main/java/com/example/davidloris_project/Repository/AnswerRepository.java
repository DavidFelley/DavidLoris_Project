package com.example.davidloris_project.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.davidloris_project.CompositeObjects.AnswerWithUsername;
import com.example.davidloris_project.Local.AnswerDAO;
import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Model.Answer;

import java.util.List;

public class AnswerRepository {
    private AnswerDAO answerDao;

    public AnswerRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        answerDao = database.answerDAO();
    }

    //Get all messages from the subject
    public LiveData<List<AnswerWithUsername>> getAllMessageFromSubject(int idSubject){
        return answerDao.getAllMessageFromSubject(idSubject);
    }

    //The insertion for the answer
    public void insert(Answer answer){
        new InsertAnswerAsyncTask(answerDao).execute(answer);
    }

    private static class InsertAnswerAsyncTask extends AsyncTask<Answer, Void, Void> {

        private AnswerDAO answerDao;

        private InsertAnswerAsyncTask(AnswerDAO answerDao){
            this.answerDao = answerDao;
        }

        @Override
        protected Void doInBackground(Answer... answers) {
            answerDao.insertAnswer(answers[0]);
            return null;
        }
    }

    //The insertion for the answer
    public void delete(int i){
        new DeleteAsyncTask(answerDao).execute(i);
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private AnswerDAO answerDao;

        private DeleteAsyncTask(AnswerDAO answerDao){
            this.answerDao = answerDao;
        }


        @Override
        protected Void doInBackground(Integer... integers) {
            answerDao.delete(integers[0]);
            return null;
        }
    }
}
