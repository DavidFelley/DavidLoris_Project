package com.example.davidloris_project.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.davidloris_project.Model.Subject;

import java.util.List;

public interface SubjectDAO {

    @Query("SELECT * FROM tabSubject ORDER BY date")
    LiveData<List<Subject>> getAllSubject();

    @Query("SELECT * FROM tabSubject WHERE idGenre=:idCategory")
    List<Subject> getAllSubjectFromCategory(int idCategory);

    @Insert
    void insertSubject(Subject subject);
}
