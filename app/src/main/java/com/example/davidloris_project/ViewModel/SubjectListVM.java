package com.example.davidloris_project.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.davidloris_project.Entity.SubjectEntity;
import com.example.davidloris_project.OnAsyncEventListener;
import com.example.davidloris_project.Repository.SubjectRepository;

import java.util.List;

public class SubjectListVM extends AndroidViewModel
{

    private SubjectRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<SubjectEntity>> observableSubjects;

    public SubjectListVM(@NonNull Application application, SubjectRepository clientRepository) {
        super(application);

        repository = clientRepository;

        observableSubjects = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableSubjects.setValue(null);

        LiveData<List<SubjectEntity>> subjects = repository.getAllSubjectsCloud();

        // observe the changes of the entities from the database and forward them
        observableSubjects.addSource(subjects, observableSubjects::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final SubjectRepository clientRepository;

        public Factory(@NonNull Application application) {
            this.application = application;
            clientRepository = SubjectRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new SubjectListVM(application, clientRepository);
        }
    }

    /**
     * Expose the LiveData ClientEntities query so the UI can observe it.
     */
    public LiveData<List<SubjectEntity>> getSubjectsCloud()
    {
        return observableSubjects;
    }


}
