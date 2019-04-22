package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.davidloris_project.BaseApp;
import com.example.davidloris_project.Entity.SubjectEntity;
import com.example.davidloris_project.Repository.SubjectRepository;

import java.util.List;

public class SubjectListVM extends AndroidViewModel
{
    private SubjectRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<SubjectEntity>> observableSubjects;

    public SubjectListVM(@NonNull Application application, final String category, SubjectRepository subjectRepository) {
        super(application);

        repository = subjectRepository;

        observableSubjects = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        observableSubjects.setValue(null);

        LiveData<List<SubjectEntity>> subjects = repository.getSubjectFromCategoryCloud(category);

        // observe the changes of the entities from the database and forward them
        observableSubjects.addSource(subjects, observableSubjects::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final SubjectRepository subjectRepository;

        private final String mCategory;

        public Factory(@NonNull Application application, String category) {
            this.application = application;
            this.mCategory = category;
            this.subjectRepository = ((BaseApp) application).getSubjectRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new SubjectListVM(application, mCategory, subjectRepository);
        }
    }

    public LiveData<List<SubjectEntity>> getSubjects()
    {
        return observableSubjects;
    }


}
