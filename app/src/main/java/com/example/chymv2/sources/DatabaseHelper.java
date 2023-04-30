package com.example.chymv2.sources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String EXERCICES_ID = "COLUMN_ID";
    public static final String EXERCICES_COLOR = "COLUMN_COLOR";
    public static final String EXERCICES_NAME = "COLUMN_EXERCICE";
    public static final String EXERCICES_GROUP = "COLUMN_GROUP";
    public static final String EXERCICES_TYPE = "COLUMN_TYPE";
    public static final String ROUTINE_ID = "COLUMN_TYPE";
    public static final String ROUTINE_COLOR = "COLUMN_COLOR";
    public static final String ROUTINE_NAME = "COLUMN_NAME";
    public static final String ROUTINE_EXERCICES = "COLUMN_EXERCICES";


    public DatabaseHelper(Context context) {
        super(context, "Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create table Exercices(" + EXERCICES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EXERCICES_COLOR + " TEXT, " + EXERCICES_NAME + " TEXT, " + EXERCICES_GROUP + " TEXT, " + EXERCICES_TYPE + " TEXT )");
        DB.execSQL("create table Routines(" + ROUTINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ROUTINE_COLOR + " TEXT, " + ROUTINE_NAME + " TEXT, " + ROUTINE_EXERCICES + " TEXT )");
        ContentValues contentValues = new ContentValues();
        initDatabaseExercices(contentValues, DB);
        contentValues = new ContentValues();
        initDatabaseRoutine(contentValues, DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists Exercices");
        DB.execSQL("drop table if exists Routines");

    }

    public Boolean insertExerciceData(String color, String exercice, String group, String type){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        dataInsertionExercices(color,exercice,group,type,contentValues);
        long result = DB.insert("Exercices", null,contentValues);
        return (result == -1);
    }

    public Boolean insertRoutineData(String color, String routine,String listExercices){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        dataInsertionRoutine(color,routine,listExercices,contentValues);
        long result = DB.insert("Routine", null,contentValues);
        return (result == -1);
    }
    public Cursor getDataExercices(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Exercices",null);
        return cursor;
    }
    public Cursor getDataRoutine(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Routines",null);
        return cursor;
    }

    private void dataInsertionExercices(String color, String exercice, String group, String type,ContentValues contentValues){

        contentValues.put(EXERCICES_COLOR,color);
        contentValues.put(EXERCICES_NAME,exercice);
        contentValues.put(EXERCICES_GROUP,group);
        contentValues.put(EXERCICES_TYPE,type);
    }

    private void dataInsertionRoutine(String color, String routine,String listExercices, ContentValues contentValues){

        contentValues.put(ROUTINE_COLOR,color);
        contentValues.put(ROUTINE_NAME,routine);
        contentValues.put(ROUTINE_EXERCICES,listExercices);
    }

    private void initDatabaseExercices(ContentValues contentValues,SQLiteDatabase DB){
        EjerciciosDBtemporal initialData = new EjerciciosDBtemporal();
        ArrayList<String> lista = initialData.dataTable();
        for(int i = 0; i<lista.size()-4;i+=4){
            dataInsertionExercices(lista.get(i),lista.get(i+1),lista.get(i+2),lista.get(i+3),contentValues);
            DB.insert("Exercices",null,contentValues);
        }
    }

    private void initDatabaseRoutine(ContentValues contentValues,SQLiteDatabase DB){
        EjerciciosDBtemporal initialData = new EjerciciosDBtemporal();
        ArrayList<String> lista = initialData.routineTable();
        for(int i = 0; i<lista.size()-3;i+=3){
            dataInsertionRoutine(lista.get(i),lista.get(i+1),lista.get(i+2),contentValues);
            DB.insert("Routines",null,contentValues);

        }
    }
}

