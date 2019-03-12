package com.example.davidloris_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Forum.db";
    private static final int DATABASE_VERSION = 1;




    public DatabaseManager (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //user (id,pseudo,email,password)
    //Subject (id, titre)
    //Categorie (id, category)
    //Message (id, message, idSujet, date et heure)

    @Override
    public void onCreate(SQLiteDatabase db) {

        String strsql1 = "create table User ( "+
                                            "IdUser integer primary key autoincrement,"
                                            + "Pseudo text not null,"
                                            + "Email text not null,"
                                            + "Password text not null"
                                            +")";

        String strsql2 = "create table Subject ( "+
                                                    "IdSubject integer primary key autoincrement,"
                                                 + "Title text not null,"
                                                 + "IdCategory integer"
                                                 +")";

        String strsql3 = "create table Category ( "+
                                                    "IdCategroy integer primary key autoincrement,"
                                                    + "Category text not null"
                                                    +")";

        String strsql4 = "create table Meassage ("+
                                                    "IdMessage integer primary key autoincrement,"
                                                    + "Message text not null,"
                                                    + "IdSubject integer,"
                                                    + "Date datetime"
                                                    +")";

        db.execSQL(strsql1);
        db.execSQL(strsql2);
        db.execSQL(strsql3);
        db.execSQL(strsql4);

        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        String insert1 = "insert into User (Pseudo, Email, Password)"+
                         "Values( 'Loris', 'lorisclivaz@hotmail.com', 'lorisclivaz')";

        String insert2 = "insert into Subject (Title, IdCatgory)"+
                         "values ('Loris aime les pommes', 1)";

        String insert3 = "insert into Category (Category)"+
                         "values('Actualite')";

        String insert4 = "insert into Message (Message, IdSubject, Date)"+
                         "values ('salut mec', 1, 17-01-2019)";

        db.execSQL(insert1);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);

        this.onCreate(db);

        Log.i("DATABASE", "onUpgrade invoked");



    }
}
