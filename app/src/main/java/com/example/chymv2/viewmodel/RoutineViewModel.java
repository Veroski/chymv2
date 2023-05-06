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
import com.example.chymv2.view.ActivityMain;

import java.util.ArrayList;
import java.util.List;

public class RoutineViewModel {
    private MutableLiveData<List<Rutina>> mRoutines;
    private List<Rutina> RoutineElements;
    private List<ListExercice> ExerciceElements;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    private InitializeData data;

    public RoutineViewModel(Context context){
        data = InitializeData.getInstance(context);
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

    public ListExercice findExerciceById(int id){
        return data.findExerciceById(id);
    }

    /*
    Este metodo peta porque eVM es una instancia vacia recien creada de ExercicesViewModel, lo que necesito es conseguir la primera instanciacion de la clase
    meter esa instancia en una clase con metodos de findCosasById y llamar a esa clase aqui.
     */
}
