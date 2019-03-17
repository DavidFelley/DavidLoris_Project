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

    private String category;

    private int idAutor;

    private String date;

    public Subject(String title, String textSubject, String category, String date){
        this.title = title;
        this.textSubject = textSubject;
        this.category = category;
        this.date = date;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public String getTitle() {
        return title;
    }

    public String getTextSubject() {
        return textSubject;
    }

    public String getCategory() {
        return category;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public String getDate() {
        return date;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTextSubject(String textSubject) {
        this.textSubject = textSubject;
    }

    public void setCategory(String category) {
        category = category;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
