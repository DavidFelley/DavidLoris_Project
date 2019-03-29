package com.example.davidloris_project.CompositeObjects;

public class AnswerWithUsername {

    //All getters and setters
    private int idAnswer;
    private String pseudo;
    private String textAnswer;
    private int idAutor;
    private int idSubject;
    private String date;

    public int getIdAnswer() {
        return idAnswer;
    }

    public String getPseudo() {
        return pseudo;
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

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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
