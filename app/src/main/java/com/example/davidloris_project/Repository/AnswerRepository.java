package com.example.davidloris_project.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.CompositeObjects.AnswerWithUsername;
import com.example.davidloris_project.Entity.AnswerEntity;
import com.example.davidloris_project.Local.AnswerDAO;
import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Model.Answer;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AnswerRepository {
    private AnswerDAO answerDao;

    private static AnswerRepository instance;

    public AnswerRepository()
    {

    }

    public static AnswerRepository getInstance()
    {

        if (instance == null) {
            synchronized (AnswerRepository.class) {
                if (instance == null) {
                    instance = new AnswerRepository();
                }
            }
        }
        return instance;
    }

    public void insertCloud(final AnswerEntity answer, final AsyncTaskListener callback)
    {

        String id = FirebaseDatabase.getInstance().getReference("answers").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("answers")
                .child(id)
                .setValue(answer, ((databaseError, databaseReference) -> {

                    if(databaseError != null)
                    {
                        callback.onFailure(databaseError.toException());
                    }else
                    {
                        callback.onSuccess();
                    }

                } ));

    }


    public void updateCloud(final AnswerEntity answer, AsyncTaskListener callback)
    {
        FirebaseDatabase.getInstance()
                .getReference("answers")
                .child(answer.getIdAnswer())
                .updateChildren(answer.toMap(), ((databaseError, databaseReference) ->
                {
                    if (databaseError != null)
                    {
                        callback.onFailure(databaseError.toException());
                    }
                    else
                    {
                        callback.onSuccess();
                    }
                }));


    }

    public void deleteCloud(final AnswerEntity answer, AsyncTaskListener callback)
    {
        FirebaseDatabase.getInstance()
                .getReference("answers")
                .child(answer.getIdAnswer())
                .removeValue(((databaseError, databaseReference) ->
                {
                    if(databaseError != null)
                    {
                        callback.onFailure(databaseError.toException());
                    }
                    else
                    {
                        callback.onSuccess();
                    }
                }));
    }


    public AnswerRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        answerDao = database.answerDAO();
    }

    public LiveData<List<AnswerWithUsername>> getAllMessageFromSubject(int idSubject) {
        return answerDao.getAllMessageFromSubject(idSubject);
    }

    public void deleteAnswer(int idAnswer) {
        new DeleteAnswerAsyncTask(answerDao).execute(idAnswer);
    }

    public void insert(Answer answer) {
        new InsertAnswerAsyncTask(answerDao).execute(answer);
    }

    public void update(Answer answer) {
        new UpdateAnswerAsyncTask(answerDao).execute(answer);
    }

    private static class InsertAnswerAsyncTask extends AsyncTask<Answer, Void, Void> {

        private AnswerDAO answerDao;

        private InsertAnswerAsyncTask(AnswerDAO answerDao) {
            this.answerDao = answerDao;
        }

        @Override
        protected Void doInBackground(Answer... answers) {
            answerDao.insertAnswer(answers[0]);
            return null;
        }
    }

    private static class UpdateAnswerAsyncTask extends AsyncTask<Answer, Void, Void> {

        private AnswerDAO answerDao;

        private UpdateAnswerAsyncTask(AnswerDAO answerDao) {
            this.answerDao = answerDao;
        }

        @Override
        protected Void doInBackground(Answer... answers) {
            answerDao.update(answers[0]);
            return null;
        }
    }

    private static class DeleteAnswerAsyncTask extends AsyncTask<Integer, Void, Void> {

        private AnswerDAO answerDao;

        private DeleteAnswerAsyncTask(AnswerDAO answerDao) {
            this.answerDao = answerDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            answerDao.delete(integers[0]);
            return null;
        }
    }
}
