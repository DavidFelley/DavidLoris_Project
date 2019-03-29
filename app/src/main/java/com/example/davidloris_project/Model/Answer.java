package com.example.davidloris_project.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "tabAnswer",foreignKeys = @ForeignKey(entity = User.class, parentColumns = "idUser", childColumns = "idAutor"),indices = {@Index("idAutor")})
public class Answer {

    //The model with getters and setters
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idAnswer;

    private String textAnswer;

    private int idAutor;

    private int idSubject;

    private String date;

    public Answer(String textAnswer, String date, int idAutor, int idSubject){
        this.textAnswer = textAnswer;
        this.date = date;
        this.idAutor = idAutor;
        this.idSubject = idSubject;
    }

    public int getIdAnswer() {
        return idAnswer;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public String getDate() {
        return date;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
