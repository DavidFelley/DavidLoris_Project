package com.example.davidloris_project.DatabaseClass;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "answer")

public class Answer {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idAnswer;


    private String textAnswer;

    private String idAutor;

    private String idSubject;

}
