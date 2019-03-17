package com.example.davidloris_project.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tabSubject")
public class Subject {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idSubject;

    private String title;

    private  String textSubject;

    private int idAutor;

    private int idGenre;


}
