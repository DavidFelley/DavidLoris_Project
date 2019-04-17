package com.example.davidloris_project.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class SubjectEntity
{
    private String idSubject;

    private String title;

    private  String textSubject;

    private String category;


    private String idAutor;

    private String date;

    public SubjectEntity()
    {

    }

    public SubjectEntity(String title, String textSubject, String category, String date, String idAutor){
        this.title = title;
        this.textSubject = textSubject;
        this.category = category;
        this.date = date;
        this.idAutor = idAutor;
    }

    @Exclude
    public String getId()
    {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextSubject() {
        return textSubject;
    }

    public void setTextSubject(String textSubject) {
        this.textSubject = textSubject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("textSubject", textSubject);
        result.put("category", category);
        result.put("idAutor", idAutor);
        result.put("date", date);


        return result;
    }
}
