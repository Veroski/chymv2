package com.example.chymv2.sources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_COLOR = "COLUMN_COLOR";
    public static final String COLUMN_EXERCICE = "COLUMN_EXERCICE";
    public static final String COLUMN_GROUP = "COLUMN_GROUP";
    public static final String COLUMN_TYPE = "COLUMN_TYPE";

    public DatabaseHelper(Context context) {
        super(context, "Exercices.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table Userdetails(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLOR + " TEXT, " + COLUMN_EXERCICE + " TEXT, " + COLUMN_GROUP + " TEXT, " + COLUMN_TYPE + " TEXT )");
        ContentValues contentValues = new ContentValues();
        initDatabase(contentValues, DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists Userdetails");
    }

    public Boolean insertExerciceData(String color, String exercice, String group, String type){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        dataInsertion(color,exercice,group,type,contentValues);
        long result = DB.insert("Userdetails", null,contentValues);
        return (result == -1);
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }

    private void dataInsertion(String color, String exercice, String group, String type,ContentValues contentValues){

        contentValues.put(COLUMN_COLOR,color);
        contentValues.put(COLUMN_EXERCICE,exercice);
        contentValues.put(COLUMN_GROUP,group);
        contentValues.put(COLUMN_TYPE,type);
    }

    private void initDatabase(ContentValues contentValues,SQLiteDatabase DB){
        EjerciciosDBtemporal initialData = new EjerciciosDBtemporal();
        ArrayList<String> lista = initialData.dataTable();
        for(int i = 0; i<lista.size()-4;i+=4){
            dataInsertion(lista.get(i),lista.get(i+1),lista.get(i+2),lista.get(i+3),contentValues);
            DB.insert("Userdetails",null,contentValues);
        }
    }
}

