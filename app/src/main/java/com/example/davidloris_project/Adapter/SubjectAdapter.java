package com.example.davidloris_project.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.davidloris_project.Model.Subject;
import com.example.davidloris_project.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    private List<Subject> subjects = new ArrayList<>();

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_item, parent, false);
        return new SubjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder subjectHolder, int position) {
        Subject currentSubject = subjects.get(position);
        subjectHolder.textViewIdSubject.setText(String.valueOf(currentSubject.getIdSubject()));
        subjectHolder.textViewTitle.setText(currentSubject.getTitle());
        subjectHolder.textViewDate.setText(currentSubject.getDate());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    class SubjectHolder extends RecyclerView.ViewHolder {
        private TextView textViewIdSubject;
        private TextView textViewTitle;
        private TextView textViewDate;


        SubjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdSubject = itemView.findViewById(R.id.text_view_idSubject);
            textViewTitle = itemView.findViewById(R.id.text_view_title_subject);
            textViewDate = itemView.findViewById(R.id.text_view_date);
        }
    }
}
