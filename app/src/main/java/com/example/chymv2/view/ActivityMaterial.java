package com.example.chymv2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;

import com.example.chymv2.R;
import com.example.chymv2.adapters.MaterialListAdapter;
import com.example.chymv2.model.CardMaterial;

import java.util.ArrayList;
import java.util.List;

public class ActivityMaterial extends AppCompatActivity {

    List<CardMaterial> elements;
    RecyclerView materialRecyclerView ;
    MaterialListAdapter materialListAdapter;
    CheckBox materialCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        init();
    }
    public void init(){
        elements = new ArrayList<>();
        elements.add(new CardMaterial("Mancuerna", false));
        elements.add(new CardMaterial("Barra de Pesas", false));
        elements.add(new CardMaterial("Barra de Dominadas", false));
        elements.add(new CardMaterial("Maquinas", false));
        elements.add(new CardMaterial("Kettlebell", false));
        elements.add(new CardMaterial("Peso Corporal", false));

        materialListAdapter = new MaterialListAdapter(elements, this);

        materialListAdapter.notifyDataSetChanged();
        materialRecyclerView = findViewById(R.id.rvMaterial);
        materialRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialRecyclerView.setHasFixedSize(true);
        materialRecyclerView.setAdapter(materialListAdapter);



    }

}