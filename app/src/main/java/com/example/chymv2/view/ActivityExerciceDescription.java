package com.example.chymv2.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chymv2.R;
import com.example.chymv2.model.ListExercice;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class ActivityExerciceDescription extends AppCompatActivity {


    TextView tvExerciceDescription;
    TextView tvExerciceMuscleGroup;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_description);

        ListExercice element = (ListExercice) getIntent().getSerializableExtra("ListExercice");
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
        tvExerciceDescription = findViewById(R.id.tvExerciceDescription);
        tvExerciceMuscleGroup = findViewById(R.id.tvGrupoMuscular);

        collapsingToolbarLayout.setTitle(element.getEjercicio());
        tvExerciceDescription.setText(element.getDescripcion());
        tvExerciceMuscleGroup.setText(element.getGrupoMuscular());
        /*
        tvExerciceTitle = findViewById(R.id.tvExerciceTitle);
        tvExerciceDescription = findViewById(R.id.tvExerciceDescription);
        tvExerciceStatus = findViewById(R.id.tvExerciceStatus);

        tvExerciceTitle.setText(element.getEjercicio());
        tvExerciceTitle.setTextColor(Color.parseColor(element.getColor()));

        tvExerciceDescription.setText(element.getDescripcion());// Ejamplo 1- Cambiar

        tvExerciceStatus.setText(element.getTipoEjercicio());// Ejamplo 2- Cambiar
        */

    }
}
