package com.example.chymv2.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chymv2.model.Ejercicio;
import com.example.chymv2.model.ListExercice;
import com.example.chymv2.model.Rutina;
import com.example.chymv2.sources.DatabaseHelper;
import com.example.chymv2.sources.EjerciciosDBtemporal;
import com.example.chymv2.sources.InitializeData;

import java.util.ArrayList;
import java.util.List;

public class RoutineViewModel {
    private MutableLiveData<List<Rutina>> mRoutines;
    private List<Rutina> RoutineElements;
    private List<ListExercice> ExerciceElements;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    private InitializeData data;

    public RoutineViewModel(Context context){
        data = new InitializeData(context);
        RoutineElements = data.getAllRoutines();
        mRoutines = new MutableLiveData<>();
        mRoutines.setValue(RoutineElements);
        ExerciceElements = data.getAllListExercice();
    }

    public LiveData<List<Rutina>> getRoutines() {
        return mRoutines;
    }

    public void addNewValue(final Rutina listElement) {

        List<Rutina> currentElement = mRoutines.getValue();
        currentElement.add(listElement);

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
        return data.findExerciceById(id);
    }

    /*
    Este metodo peta porque eVM es una instancia vacia recien creada de ExercicesViewModel, lo que necesito es conseguir la primera instanciacion de la clase
    meter esa instancia en una clase con metodos de findCosasById y llamar a esa clase aqui.
     */
}
