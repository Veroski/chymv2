package com.example.chymv2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chymv2.R;
import com.example.chymv2.adapters.MaterialAdapter;
import com.example.chymv2.model.CardMaterial;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity {

    List<CardMaterial> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_material);

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

        MaterialAdapter materialAdapter = new MaterialAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(materialAdapter);
    }

}