package com.example.davidloris_project.DatabaseClass;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "category")
public class Category
{

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int idCategory;

    @ColumnInfo(name = "username")
    private String categoryName;




}
