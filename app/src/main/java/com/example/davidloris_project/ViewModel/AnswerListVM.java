package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.davidloris_project.BaseApp;
import com.example.davidloris_project.Entity.AnswerEntity;
import com.example.davidloris_project.Repository.AnswerRepository;

import java.util.List;

public class AnswerListVM extends AndroidViewModel {

    private AnswerRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<AnswerEntity>> observableAnswers;

    public AnswerListVM(@NonNull Application application, final String idSubject, AnswerRepository answerRepository) {
        super(application);

        repository = answerRepository;

        observableAnswers = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        observableAnswers.setValue(null);

        LiveData<List<AnswerEntity>> answers = repository.getAnswerFromSubject(idSubject);

        // observe the changes of the entities from the database and forward them
        observableAnswers.addSource(answers, observableAnswers::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final AnswerRepository answerRepository;

        private final String mIdSubject;

        public Factory(@NonNull Application application, String idSubject) {
            this.application = application;
            this.mIdSubject = idSubject;
            this.answerRepository = ((BaseApp) application).getAnswerRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new AnswerListVM(application, mIdSubject, answerRepository);
        }
    }

    public LiveData<List<AnswerEntity>> getAnswers()
    {
        return observableAnswers;
    }


}
