package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davidloris_project.Adapter.SubjectAdapter;
import com.example.davidloris_project.Activity.AddSubjectActivity;
import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.SubjectVM;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.davidloris_project.Fragment.LoginFragment.USER_ID;

public class ListSubjectFragment extends Fragment {
    public static final int ADD_SUBJECT_REQUEST = 1;

    private SubjectVM subjectVM;
    private String category;
    private DateFormat date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.getDefault());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        /* Get the category name for the query */
        category = getArguments().getString("CategoryName");

        final View categoryView = inflater.inflate(R.layout.fragment_listsubject, container, false);

        RecyclerView recyclerView = categoryView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final SubjectAdapter adapter = new SubjectAdapter();
        recyclerView.setAdapter(adapter);

        subjectVM = ViewModelProviders.of(this).get(SubjectVM.class);

        /* Method that keep the fragment up to date whenever their is a new subject inserted */
        subjectVM.getAllSubjectsFromCategory(category).observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(@Nullable List<Subject> subjects) {
                adapter.setSubjects(subjects);
            }
        });

        /* The button that open the addSubject activity */
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

    /* This method create the insertion that the activity AddSubject send back*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* First if is when the user click on backButton */
        if (data != null) {
            if (requestCode == ADD_SUBJECT_REQUEST || resultCode == getActivity().RESULT_OK) {
                String title = data.getStringExtra(AddSubjectActivity.EXTRA_TITLE);
                String message = data.getStringExtra(AddSubjectActivity.EXTRA_MESSAGE);

                String PostingDate = date.format(Calendar.getInstance().getTime());

                Subject subject = new Subject(title, message, category, PostingDate,USER_ID);

                subjectVM.insert(subject);

                Toast.makeText(getActivity(), "Subject posted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "A wild problem appeared !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
