package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidloris_project.Activity.AddSubjectActivity;
import com.example.davidloris_project.Adapter.MessageAdapter;
import com.example.davidloris_project.Adapter.SubjectAdapter;
import com.example.davidloris_project.Model.Answer;
import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.AnswerVM;
import com.example.davidloris_project.ViewModel.SubjectVM;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class InSubjectFragment extends Fragment {

    private SubjectVM subjectVM;
    private AnswerVM answerVM;
    private int idSubject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* Get the id of the subject that was clicked in the last fragment */
        idSubject = getArguments().getInt("idSubject");

        final View inSubjectView = inflater.inflate(R.layout.fragment_insubject, container, false);

        RecyclerView recyclerView = inSubjectView.findViewById(R.id.recycler_viewInSubject);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final MessageAdapter adapter = new MessageAdapter();
        recyclerView.setAdapter(adapter);

        answerVM = ViewModelProviders.of(this).get(AnswerVM.class);

        /* Method that keep the fragment up to date whenever their is a new subject inserted */
        answerVM.getAllMessageFromSubject(idSubject).observe(this, new Observer<List<Answer>>() {
            @Override
            public void onChanged(@Nullable List<Answer> messages) {
                adapter.setMessages(messages);
            }
        });

        return inSubjectView;
    }
}
