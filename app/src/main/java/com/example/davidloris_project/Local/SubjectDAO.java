package com.example.davidloris_project.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.CompositeObjects.SubjectWithUserName;
import com.example.davidloris_project.Model.Subject;

import java.util.List;

@Dao
public interface SubjectDAO {

    @Query("SELECT * FROM tabSubject WHERE category=:category ORDER BY date DESC")
    LiveData<List<Subject>> getAllSubjectsFromCategory(String category);

    @Query("SELECT idSubject, username as pseudo, textSubject, idAutor, date FROM tabSubject tA, tabUsers tU WHERE idSubject=:idSubject AND tA.idAutor = tU.idUser ORDER BY date")
    LiveData<SubjectWithUserName> getSubjectById(int idSubject);

    @Insert
    void insertSubject(Subject subject);
}
