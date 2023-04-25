package com.example.chymv2.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chymv2.model.ListExercice;
import com.example.chymv2.sources.DatabaseHelper;
import com.example.chymv2.sources.EjerciciosDBtemporal;

import java.util.ArrayList;
import java.util.List;

public class ExercicesViewModel extends ViewModel {
    private MutableLiveData<List<ListExercice>> mExercices;
    private List<ListExercice> elements;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    private EjerciciosDBtemporal DB;
    private DatabaseHelper databaseHelper;

    public static final String SEPARATOR = ";";
    public static final String QUOTE = "\"";

    public void init(Context context) {
        elements = new ArrayList<ListExercice>();
        databaseHelper = new DatabaseHelper(context);
        displayData(context);
        mExercices = new MutableLiveData<>();
        mExercices.setValue(elements);

    }

    public LiveData<List<ListExercice>> getExercices() {
        return mExercices;
    }

    public void addNewValue(final ListExercice listElement) {

        List<ListExercice> currentElement = mExercices.getValue();
        currentElement.add(listElement);

    }


    private void displayData(Context context) {
        Cursor cursor = databaseHelper.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                String color = cursor.getString(1);
                String exercice = cursor.getString(2);
                String group = cursor.getString(3);
                String type = cursor.getString(4);
                elements.add(new ListExercice(color, exercice, group, type));

            }
        }

    }

}

