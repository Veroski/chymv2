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
import com.example.chymv2.sources.InitializeData;

import java.util.ArrayList;
import java.util.List;

public class ExercicesViewModel extends ViewModel {
    private MutableLiveData<List<ListExercice>> mExercices;
    private List<ListExercice> elements;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    private DatabaseHelper databaseHelper;
    private InitializeData data;
    public ExercicesViewModel(Context context){
        data = new InitializeData(context);
        databaseHelper = new DatabaseHelper(context);
        elements = data.getAllListExercice();
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


}

