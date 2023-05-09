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
    private Context context;

    public RoutineViewModel(Context context, int vLista){
        this.context = context;
        data = InitializeData.getInstance(context);
        actualizarLista(vLista);
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

    public List<Rutina> listaRutinasCorrespondiente(int vLista){
        List<Rutina> rutinas = new ArrayList<>();
        switch (vLista){
            case 0:

                rutinas = getRoutineByIdList("0");

                break;
            case 1:
                //Rutinas comunidad
                rutinas = getRoutineByIdList("1");
                break;
            case 2:
                //Mis Rutinas
                rutinas = getRoutineByIdList("2");
                break;
            default:
                rutinas = null;
                break;
        }


        return rutinas;
    }
    public void actualizarLista(int vLista){
        RoutineElements = listaRutinasCorrespondiente(vLista);
        mRoutines = new MutableLiveData<>();
        mRoutines.setValue(RoutineElements);
    }
    public void addRoutine(Rutina rutina){

        RoutineElements.add(rutina);
        mRoutines.setValue(RoutineElements);
    }
    public List<Rutina> getRoutineByIdList(String idList){
        List<Rutina> rutinas = new ArrayList<>();
        for(Rutina i: data.getAllRoutines()){
            if(i.getIdList().equals(idList)){
                rutinas.add(i);
            }
        }
        return rutinas;
    }

}
