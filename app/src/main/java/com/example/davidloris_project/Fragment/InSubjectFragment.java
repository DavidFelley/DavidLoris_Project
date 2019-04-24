package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidloris_project.Activity.AddEditAnswerActivity;
import com.example.davidloris_project.Activity.AddSubjectActivity;
import com.example.davidloris_project.Adapter.MessageAdapter;
import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.AnswerEntity;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.AnswerListVM;
import com.example.davidloris_project.ViewModel.AnswerVM;
import com.example.davidloris_project.ViewModel.SubjectVM;
import com.example.davidloris_project.ViewModel.UserVM;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class InSubjectFragment extends Fragment {
    public static final int ADD_ANSWER_REQUEST = 1;
    public static final int EDIT_ANSWER_REQUEST = 2;

    private List<AnswerEntity> answers;
    private AnswerVM answerVM;
    private AnswerListVM answerListVM;
    private UserVM userVM;
    private String idSubject;
    private DateFormat date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.getDefault());
    private AnswerEntity savedAnswer;

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

        final MessageAdapter adapter = new MessageAdapter(userVM);

        answerVM = ViewModelProviders.of(this).get(AnswerVM.class);

        SubjectVM subjectVM = ViewModelProviders.of(this).get(SubjectVM.class);
        userVM = ViewModelProviders.of(this).get(UserVM.class);

        /* Show subject on top of the page */
        subjectVM.getSubjectByIdCloud(idSubject).observe(this, subjectEntity -> {
            textViewTitleSubject.setText(subjectEntity.getTitle());
            textViewContentSubject.setText(subjectEntity.getTextSubject());
            userVM.getUsername(subjectEntity.getIdAutor()).observe(this, s -> textViewPseudoSubject.setText(s));
            textViewDateSubject.setText(subjectEntity.getDate());
        });

        answers = new ArrayList<>();

        /* Method that keep the fragment up to date whenever their is a new subject inserted */
        AnswerListVM.Factory factory = new AnswerListVM.Factory(
                getActivity().getApplication(), idSubject);
        answerListVM = ViewModelProviders.of(this, factory).get(AnswerListVM.class);
        answerListVM.getAnswers().observe(this, answerEntities -> {
            if (answerEntities != null) {
                answers = answerEntities;
                adapter.setMessages(answers);
            }
        });

        recyclerView.setAdapter(adapter);

        /* The button that open the addAnswer activity */
        FloatingActionButton buttonAddAnswer = inSubjectView.findViewById(R.id.button_add_answer);
        buttonAddAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditAnswerActivity.class);
                startActivityForResult(intent, ADD_ANSWER_REQUEST);
            }
        });

        adapter.setOnItemClickListener(answer -> {
            if (answer.getIdAutor().equals(FirebaseAuth.getInstance().getUid())) {
                savedAnswer = answer;
                Intent intent = new Intent(getActivity(), AddEditAnswerActivity.class);
                intent.putExtra(AddEditAnswerActivity.EXTRA_IDMESSAGE, answer.getIdAnswer());
                intent.putExtra(AddEditAnswerActivity.EXTRA_MESSAGE, answer.getTextAnswer());
                startActivityForResult(intent, EDIT_ANSWER_REQUEST);
            }
            Log.i("**** CA MARCHE PAS ****", "CA MARCHE PAS DU TOUUUUUUUUUUUUUUUUUUUUUUT XXXXXXXXXXXXXXXXXXXX");
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

                String idAutor = FirebaseAuth.getInstance().getUid();
                AnswerEntity answer = new AnswerEntity(message, idAutor, idSubject, PostingDate);

                answerVM.insertCloud(answer, new AsyncTaskListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.i("Fail posting answer", e.getMessage());
                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(), "Answer posted", Toast.LENGTH_SHORT).show();
                    }
                });
                /* Check for edit request */
            } else if (requestCode == EDIT_ANSWER_REQUEST && resultCode == getActivity().RESULT_OK) {
                String idAnswer = data.getStringExtra(AddEditAnswerActivity.EXTRA_IDMESSAGE);
                Log.i("ID ANSWER", idAnswer);
                int delete = data.getIntExtra(AddEditAnswerActivity.EXTRA_DELETE_MESSAGE, -1);
                if (idAnswer == "") {
                    Toast.makeText(getActivity(), "Answer can't be updated", Toast.LENGTH_SHORT).show();
                    return;
                }
                /* With delete you can edit or delete an answer so check if the request was to delete */
                if (delete == 4) {

                    answerVM.deleteCloud(idAnswer, new AsyncTaskListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Log.i("Fail deleting answer", e.getMessage());
                        }

                        @Override
                        public void onSuccess() {
                            Toast.makeText(getActivity(), "Answer deleted", Toast.LENGTH_SHORT).show();
                        }
                    });

                    /* if it wasn't to delete then they wanted to edit */
                } else {

                    String message = data.getStringExtra(AddEditAnswerActivity.EXTRA_MESSAGE);

                    AnswerEntity answer = new AnswerEntity(message, savedAnswer.getIdAutor(), savedAnswer.getIdSubject(), savedAnswer.getDate());
                    answer.setIdAnswer(idAnswer);

                    answerVM.updateCloud(answer, new AsyncTaskListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Log.i("Fail updating answer", e.getMessage());
                        }

                        @Override
                        public void onSuccess() {
                            Toast.makeText(getActivity(), "Note updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            } else {
                Toast.makeText(getActivity(), "A wild problem appeared !", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
