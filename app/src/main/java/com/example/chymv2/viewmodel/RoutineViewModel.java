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
import com.example.chymv2.view.ActivityMisRutinas;

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
        mRoutines = new MutableLiveData<>();
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
                //Rutinas recomendadas
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

        mRoutines.setValue(RoutineElements);
    }
    public void addRoutine(Rutina rutina){

        RoutineElements.add(rutina);
        mRoutines.setValue(RoutineElements);
        InitializeData.getInstance(context).addRoutineData(rutina);

        InitializeData.getDbInstance(context).insertRoutineData(rutina.getColor(),rutina.getNombre(),ListaEjerciciosToStringId(rutina),rutina.getIdList());
        actualizarLista(Integer.parseInt(rutina.getIdList()));
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
    public String ListaEjerciciosToStringId(Rutina rutina){
        String ids ="";
        List<ListExercice> lista= rutina.getEjercicios();
        for(int i=0;i<lista.size();i++){
            ids += lista.get(i).getId();
            if(i < lista.size() -1){
                ids += ",";
            }
        }
        return ids;
    }
    public Rutina crearRutina(String color, String name, String listEx, String idList){

        return new Rutina(color,name,StringToExercices(listEx),idList);
    }
    public ArrayList<ListExercice> StringToExercices(String listEx){
        ArrayList<ListExercice> listExercices = new ArrayList<>();
        String almacenar = "";
        for(int i = 0; i<listEx.length();i++){
            if(listEx.charAt(i) == ','){
                listExercices.add(findExerciceById(Integer.parseInt(almacenar)));
                almacenar = "";
            }
            else{
                almacenar += listEx.charAt(i);
            }
        }
        return listExercices;
    }

}
