package com.example.chymv2.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chymv2.R;


public class ActivityExerciceDescription extends AppCompatActivity {

    TextView tvExerciceTitle;
    TextView tvExerciceDescription;
    TextView tvExerciceStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_description);
        /*
        ListExercice element = (ListExercice) getIntent().getSerializableExtra("ListExercice");

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
