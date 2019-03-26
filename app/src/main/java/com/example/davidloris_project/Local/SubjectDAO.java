package com.example.davidloris_project.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.Model.Subject;

import java.util.List;

@Dao
public interface SubjectDAO {

    @Query("SELECT * FROM tabSubject WHERE category=:category ORDER BY date DESC")
    LiveData<List<Subject>> getAllSubjectsFromCategory(String category);

    @Query("SELECT * FROM tabSubject WHERE idSubject=:idSubject")
    LiveData<Subject> getSubjectById(int idSubject);

    @Insert
    void insertSubject(Subject subject);
}
