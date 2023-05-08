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
        RoutineElements = listaRutinasCorrespondiente(vLista);
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

    public List<Rutina> listaRutinasCorrespondiente(int vLista){
        List<Rutina> rutinas;
        switch (vLista){
            case 0:
                //Rutinas recomendadas
                rutinas = new ArrayList<>();
                ArrayList<ListExercice> ejersisios = new ArrayList<>();
                ejersisios.add(findExerciceById(100));
                ejersisios.add(findExerciceById(90));
                ejersisios.add(findExerciceById(57));
                ejersisios.add(findExerciceById(15));
                ejersisios.add(findExerciceById(18));
                ejersisios.add(findExerciceById(5));

                rutinas.add(new Rutina("#000000","Celao's Rutine",ejersisios));


                /*
                DatabaseHelper testing = InitializeData.getDbInstance(context);
                testing.insertRoutineData("#000000","Celao's Rutine","100,90,57,15,18,5");
                Hay que hacer un id en el constructor de Rutinas para diferenciar entre la lista que estamos usando (0 mis rutinas, 2 rutinas recomendadas, 3 ambas)
                 */
                break;
            case 1:
                //Rutinas comunidad
                rutinas = new ArrayList<>();
                break;
            case 2:
                //Mis Rutinas

                rutinas = data.getAllRoutines();
                break;
            default:
                rutinas = null;
                break;
        }

        return rutinas;
    }
    public void addRoutine(Rutina rutina){

        RoutineElements.add(rutina);
        mRoutines.setValue(RoutineElements);
    }

}
