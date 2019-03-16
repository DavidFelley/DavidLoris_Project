package com.example.davidloris_project.DatabaseClass;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "subject")

public class Subject {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idSubject")
    private int id;

    @ColumnInfo(name = "titleSubject")
    private String username;

    @ColumnInfo(name = "textSubject")
    private  String text;

    @ColumnInfo (name = "idAutor")
    private int idAutor;

    @ColumnInfo (name = "idGenre")
    private int idGenre;


}
