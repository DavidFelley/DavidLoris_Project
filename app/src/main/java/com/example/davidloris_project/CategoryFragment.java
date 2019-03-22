package com.example.davidloris_project;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.ViewModel.SubjectVM;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CategoryFragment extends Fragment {
    public static final int ADD_SUBJECT_REQUEST = 1;

    private SubjectVM subjectVM;

    private DateFormat date = new SimpleDateFormat("dd.mm.yyyy");
    private String PostingDate = date.format(Calendar.getInstance().getTime());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        final View categoryView = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView recyclerView = categoryView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final SubjectAdapter adapter = new SubjectAdapter();
        recyclerView.setAdapter(adapter);

        subjectVM = ViewModelProviders.of(this).get(SubjectVM.class);
        subjectVM.getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(@Nullable List<Subject> subjects) {
                adapter.setSubjects(subjects);
            }
        });

        FloatingActionButton buttonAddSubject = categoryView.findViewById(R.id.button_add_subject);
        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddSubjectActivity.class);
                startActivityForResult(intent, ADD_SUBJECT_REQUEST);
            }
        });

        return categoryView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SUBJECT_REQUEST || resultCode == getActivity().RESULT_OK) {
            String title = data.getStringExtra(AddSubjectActivity.EXTRA_TITLE);
            String message = data.getStringExtra(AddSubjectActivity.EXTRA_MESSAGE);

            Subject subject = new Subject(title, message, "Actuality", PostingDate);

            subjectVM.insert(subject);

            Toast.makeText(getActivity(), "Subject posted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "A wild problem appeared !", Toast.LENGTH_SHORT).show();
        }
    }
}
