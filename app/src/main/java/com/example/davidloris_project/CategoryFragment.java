package com.example.davidloris_project;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.ViewModel.SubjectVM;

import java.util.List;

public class CategoryFragment extends Fragment {

    private SubjectVM subjectVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        subjectVM = ViewModelProviders.of(this).get(SubjectVM.class);
        subjectVM.getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(@Nullable List<Subject> subjects) {
                //Update recycler view
                Toast.makeText(getActivity(), "Ti", Toast.LENGTH_SHORT).show();
            }
        });

        return inflater.inflate(R.layout.fragment_category, container, false);
    }
}
