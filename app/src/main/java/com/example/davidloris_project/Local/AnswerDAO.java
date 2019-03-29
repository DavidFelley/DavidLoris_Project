package com.example.davidloris_project.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.CompositeObjects.AnswerWithUsername;
import com.example.davidloris_project.Model.Answer;
import java.util.List;

@Dao
public interface AnswerDAO {

    //All queries for the database
    @Query("SELECT idAnswer, username as pseudo, textAnswer, idAutor, idSubject, date FROM tabAnswer tA, tabUsers tU WHERE idSubject=:idSubject AND tA.idAutor = tU.idUser ORDER BY date")
    LiveData<List<AnswerWithUsername>> getAllMessageFromSubject(int idSubject);

    @Insert
    void insertAnswer(Answer answer);

    @Query("DELETE FROM tabAnswer WHERE idAnswer=:idanswer")
    void delete(int idanswer);
}
