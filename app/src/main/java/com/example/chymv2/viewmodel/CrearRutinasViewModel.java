package com.example.chymv2.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chymv2.model.ListExercice;
import com.example.chymv2.sources.InitializeData;

import java.util.ArrayList;
import java.util.List;

public class CrearRutinasViewModel {
    private MutableLiveData<List<ListExercice>> mExercices;
    private List<ListExercice> elements;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    private InitializeData data;
    public CrearRutinasViewModel(Context context){
        data = InitializeData.getInstance(context);
        elements = new ArrayList<>();
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
    public void setElements(List<ListExercice> items){
        elements = items;
    }
}
