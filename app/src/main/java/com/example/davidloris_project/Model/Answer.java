package com.example.davidloris_project.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "tabAnswer")
public class Answer {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idAnswer;

    private String textAnswer;

    private String idAutor;

    private String idSubject;

}
