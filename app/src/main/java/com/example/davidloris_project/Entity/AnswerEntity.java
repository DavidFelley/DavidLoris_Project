package com.example.davidloris_project.Entity;

import com.example.davidloris_project.Model.Answer;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class AnswerEntity
{

    private String idAnswer;

    private String textAnswer;

    private String idAutor;

    private String idSubject;

    private String date;

    public AnswerEntity(){}

    public AnswerEntity(String textAnswer, String idAutor, String idSubject, String date)
    {
        this.textAnswer = textAnswer;
        this.idAutor = idAutor;
        this.idSubject = idSubject;
        this.date = date;
    }


    @Exclude
    public String getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(String idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap()
    {
        HashMap<String, Object> result = new HashMap<>();
        result.put("textAnswer", textAnswer);
        result.put("idAutor", idAutor);
        result.put("idSubject", idSubject);
        result.put("date", date);

        return result;
    }
}
