package com.example.chymv2.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chymv2.model.ListExercice;
import com.example.chymv2.model.Rutina;
import com.example.chymv2.sources.DatabaseHelper;
import com.example.chymv2.sources.EjerciciosDBtemporal;

import java.util.ArrayList;
import java.util.List;

public class RoutineViewModel {
    private MutableLiveData<List<Rutina>> mRoutines;
    private List<Rutina> elements;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    private DatabaseHelper databaseHelper;


    public void init(Context context) {
        elements = new ArrayList<>();
        databaseHelper = new DatabaseHelper(context);
        displayData(context);
        mRoutines = new MutableLiveData<>();
        mRoutines.setValue(elements);

    }

    public LiveData<List<Rutina>> getRoutines() {
        return mRoutines;
    }

    public void addNewValue(final Rutina listElement) {

        List<Rutina> currentElement = mRoutines.getValue();
        currentElement.add(listElement);

    }


    private void displayData(Context context) {
        Cursor cursor = databaseHelper.getDataRoutine();
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {

                String color = cursor.getString(1);
                String routine = cursor.getString(2);
                elements.add(new Rutina(color, routine));

            }
        }
    }
}
