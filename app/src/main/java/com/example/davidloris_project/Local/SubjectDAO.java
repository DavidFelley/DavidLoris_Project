package com.example.davidloris_project.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.Model.Subject;

import java.util.List;

@Dao
public interface SubjectDAO {

    @Query("SELECT * FROM tabSubject ORDER BY date")
    LiveData<List<Subject>> getAllSubjects();

    @Query("SELECT * FROM tabSubject WHERE category=:category")
    List<Subject> getAllSubjectFromCategory(String category);

    @Insert
    void insertSubject(Subject subject);
}
