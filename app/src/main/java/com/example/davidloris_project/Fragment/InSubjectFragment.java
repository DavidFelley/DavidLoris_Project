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
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidloris_project.Activity.AddEditAnswerActivity;
import com.example.davidloris_project.Activity.AddSubjectActivity;
import com.example.davidloris_project.Adapter.MessageAdapter;
import com.example.davidloris_project.CompositeObjects.AnswerWithUsername;
import com.example.davidloris_project.CompositeObjects.SubjectWithUserName;
import com.example.davidloris_project.Entity.SubjectEntity;
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
    public static final int ADD_ANSWER_REQUEST = 1;
    public static final int EDIT_ANSWER_REQUEST = 2;

    private AnswerVM answerVM;
    private String idSubject;
    private DateFormat date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.getDefault());
    private AnswerWithUsername savedAnswer;

    //we create the view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* Get the id of the subject that was clicked in the last fragment */
        if (getArguments() != null) {
            idSubject = getArguments().getString("idSubject");
        }

        final View inSubjectView = inflater.inflate(R.layout.fragment_insubject, container, false);

        final TextView textViewTitleSubject = inSubjectView.findViewById(R.id.text_view_title_subject_inMessage);
        final TextView textViewContentSubject = inSubjectView.findViewById(R.id.text_view_messageFromSubject);
        final TextView textViewPseudoSubject = inSubjectView.findViewById(R.id.text_view_pseudoFromSubject);
        final TextView textViewDateSubject = inSubjectView.findViewById(R.id.text_view_dateFromSubject);

        RecyclerView recyclerView = inSubjectView.findViewById(R.id.recycler_viewInSubject);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final MessageAdapter adapter = new MessageAdapter();
        recyclerView.setAdapter(adapter);


        answerVM = ViewModelProviders.of(this).get(AnswerVM.class);
        SubjectVM subjectVM = ViewModelProviders.of(this).get(SubjectVM.class);

        /* Show subject on top of the page */
        subjectVM.getSubjectByIdCloud(idSubject).observe(this, (Observer<SubjectEntity>) subjectWithUserName -> {
            textViewTitleSubject.setText(subjectWithUserName.getTitle());
            textViewContentSubject.setText(subjectWithUserName.getTextSubject());
            textViewPseudoSubject.setText(subjectWithUserName.getIdAutor());
            textViewDateSubject.setText(subjectWithUserName.getDate());
        });

        /* Method that keep the fragment up to date whenever their is a new subject inserted */
        answerVM.getAllMessageFromSubject(1).observe(this, new Observer<List<AnswerWithUsername>>() {
            @Override
            public void onChanged(@Nullable List<AnswerWithUsername> messages) {
                adapter.setMessages(messages);
            }
        });

        /* The button that open the addAnswer activity */
        FloatingActionButton buttonAddSubject = inSubjectView.findViewById(R.id.button_add_answer);
        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditAnswerActivity.class);
                startActivityForResult(intent, ADD_ANSWER_REQUEST);
            }
        });

        adapter.setOnItemClickListener(new MessageAdapter.onItemClickListener() {
            @Override
            public void onItemClick(AnswerWithUsername answer) {
                if (answer.getIdAutor() == 1 /*A REMPLACER PAR STRING ID*/) {
                    savedAnswer = answer;
                    Intent intent = new Intent(getActivity(), AddEditAnswerActivity.class);
                    intent.putExtra(AddEditAnswerActivity.EXTRA_IDMESSAGE, answer.getIdAnswer());
                    intent.putExtra(AddEditAnswerActivity.EXTRA_MESSAGE, answer.getTextAnswer());
                    startActivityForResult(intent, EDIT_ANSWER_REQUEST);
                }
            }
        });

        return inSubjectView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* First if is when the user click on backButton */
        if (data != null) {
            /* Check if we want to add an answer or edit one */
            if (requestCode == ADD_ANSWER_REQUEST && resultCode == getActivity().RESULT_OK) {

                String message = data.getStringExtra(AddSubjectActivity.EXTRA_MESSAGE);
                String PostingDate = date.format(Calendar.getInstance().getTime());


                Answer answer = new Answer(message, PostingDate, 1 /*A REMPLACER PAR STRING ID*/, 1);

                answerVM.insert(answer);

                Toast.makeText(getActivity(), "Answer posted", Toast.LENGTH_SHORT).show();
                /* Check for edit request */
            } else if (requestCode == EDIT_ANSWER_REQUEST && resultCode == getActivity().RESULT_OK) {
                int idAnswer = data.getIntExtra(AddEditAnswerActivity.EXTRA_IDMESSAGE, -1);
                int delete = data.getIntExtra(AddEditAnswerActivity.EXTRA_DELETE_MESSAGE, -1);
                if (idAnswer == -1) {
                    Toast.makeText(getActivity(), "Answer can't be updated", Toast.LENGTH_SHORT).show();
                    return;
                }
                /* With delete you can edit or delete an answer so check if the request was to delete */
                if (delete == 4) {
                    answerVM.delete(idAnswer);
                    Toast.makeText(getActivity(), "Answer deleted", Toast.LENGTH_SHORT).show();
                /* if it wasn't to delete then they wanted to edit */
                } else {

                    String message = data.getStringExtra(AddEditAnswerActivity.EXTRA_MESSAGE);

                    Answer answer = new Answer(message, savedAnswer.getDate(), savedAnswer.getIdAutor(), savedAnswer.getIdSubject());
                    answer.setIdAnswer(idAnswer);

                    answerVM.update(answer);

                    Toast.makeText(getActivity(), "Note updated", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), "A wild problem appeared !", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
