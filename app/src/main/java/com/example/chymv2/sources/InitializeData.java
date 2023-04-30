package com.example.chymv2.sources;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.chymv2.model.ListExercice;
import com.example.chymv2.model.Rutina;

import java.util.ArrayList;
import java.util.List;

public class InitializeData {
    private List<ListExercice> exercices;
    private List<Rutina> routines;
    private DatabaseHelper databaseHelper;
    private Context context;

    public InitializeData(Context context){

        this.context = context;
        //Base de datos
        databaseHelper = new DatabaseHelper(context);

        //Listas de los objetos
        exercices = new ArrayList<>();
        routines = new ArrayList<>();

        //Llenar las listas con la información de la base de datos
        displayExercicesData();
        displayRoutinesData();
    }



    private void displayExercicesData() {
        Cursor cursor = databaseHelper.getDataExercices();
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String color = cursor.getString(1);
                String exercice = cursor.getString(2);
                String group = cursor.getString(3);
                String type = cursor.getString(4);

                exercices.add(new ListExercice(color, exercice, group, type,id));
            }
        }
    }

    private void displayRoutinesData() {
        Cursor cursor = databaseHelper.getDataRoutine();
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                String color = cursor.getString(1);
                String routine = cursor.getString(2);
                String listExercices = cursor.getString(3);
                ArrayList<ListExercice> exercices = conversorStringToExercice(listExercices);
                routines.add(new Rutina(color, routine, exercices));
            }
        }
    }

    public ArrayList<ListExercice>conversorStringToExercice(String lista){
        String id = "";
        ArrayList<ListExercice> listExercices = new ArrayList<>();
        int j = 0;
        for(int i = 0; i<lista.length();i++){
            if(lista.charAt(i) == ',' || i == lista.length()-1){

                int traductor = Integer.parseInt(id);
                listExercices.add(findExerciceById(traductor));
                id = "";
                j++;
            }
            else{
                id += lista.charAt(i);
            }
        }
        return listExercices;

    }

    public ListExercice findExerciceById(int id){
        for(ListExercice i:exercices){
            if(i.getId() == id){
                return i;
            }
        }
        return null;
    }

    public List<ListExercice> getAllListExercice(){
        return exercices;
    }
    public List<Rutina> getAllRoutines(){
        return routines;
    }
}
