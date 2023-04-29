package com.example.chymv2.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chymv2.R;
import com.example.chymv2.model.Rutina;

public class RoutineDescriptionActivity extends AppCompatActivity {
    TextView tvRoutineTitle;
    TextView tvRoutineDescription;
    TextView tvRoutineStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_description);

        Rutina element = (Rutina) getIntent().getSerializableExtra("Rutina");
        tvRoutineTitle = findViewById(R.id.tvRoutineTitle);
        tvRoutineDescription = findViewById(R.id.tvRoutineDescription);
        tvRoutineStatus = findViewById(R.id.tvRoutineStatus);

        tvRoutineTitle.setText(element.getNombre());
        tvRoutineTitle.setTextColor(Color.parseColor(element.getColor()));

        tvRoutineDescription.setText(element.getNombre());// Ejamplo 1- Cambiar

        tvRoutineStatus.setText(element.getNombre());// Ejamplo 2- Cambiar
        tvRoutineStatus.setTextColor(Color.RED);
    }
}
