package com.example.davidloris_project.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.Model.Answer;

import java.util.List;

@Dao
public interface AnswerDAO {

    @Query("SELECT * FROM tabAnswer WHERE idSubject=:idSubject ORDER BY date DESC")
    LiveData<List<Answer>> getAllMessageFromSubject(int idSubject);

    @Insert
    void insertAnswer(Answer answer);
}
