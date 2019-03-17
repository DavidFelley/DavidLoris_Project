package com.example.davidloris_project.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tabCategory")
public class Category
{

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idCategory;

    private String categoryName;
}
