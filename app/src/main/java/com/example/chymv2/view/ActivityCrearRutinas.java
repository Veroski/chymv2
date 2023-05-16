package com.example.chymv2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chymv2.R;
import com.example.chymv2.model.Rutina;
import com.example.chymv2.viewmodel.RoutineViewModel;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityCrearRutinas extends AppCompatActivity {

    private Button returnMain_crearRutinas_btn, btnCrearRutina;
    private TextInputEditText tvColorRoutine, tvNameRoutine,tvRoutineType, tvExerciceListRoutine;
    private NavigationBarView navigation;
    private RoutineViewModel routineViewModel;
    private String color, nombre,routineType,listaEjs, idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rutinas);
        routineViewModel = new RoutineViewModel(this,2);
        navigation = findViewById(R.id.bottom_navigation_crearRutinas);

        tvColorRoutine = findViewById(R.id.tvColorRoutine);
        tvRoutineType = findViewById(R.id.tvTypeRoutine);
        tvNameRoutine = findViewById(R.id.tvNameRoutine);
        tvExerciceListRoutine = findViewById(R.id.tvListaEjercicios);

        returnMain_crearRutinas_btn = findViewById(R.id.returnMain_crearRutinas_btn);
        btnCrearRutina = findViewById(R.id.btnRoutineCreate);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        onClickListeners();


    }

    private final NavigationBarView.OnItemSelectedListener mOnNavigationItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.exercices_fragment:
                    startActivity(new Intent(ActivityCrearRutinas.this, ActivityMain.class).putExtra("parametro",2));
                    finish();
                    return true;
                case R.id.profile_fragment:
                    startActivity(new Intent(ActivityCrearRutinas.this, ActivityMain.class).putExtra("parametro",3));
                    finish();
                    return true;
                case R.id.routine_fragment:
                    startActivity(new Intent(ActivityCrearRutinas.this, ActivityMain.class).putExtra("parametro",1));
                    finish();
                    return true;
            }
            return false;
        }
    };
    public void onClickListeners(){
        returnMain_crearRutinas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCrearRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = tvColorRoutine.getText().toString();
                nombre = tvNameRoutine.getText().toString();
                routineType = tvRoutineType.getText().toString();
                listaEjs = tvExerciceListRoutine.getText().toString();
                idList = "2";
                boolean problemsAdding = routineViewModel.addRoutine(routineViewModel.crearRutina(color,nombre,routineType,listaEjs,idList));
                if(!problemsAdding){
                    Toast.makeText(ActivityCrearRutinas.this,"Rutina añadida correctamente a Mis Rutinas", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ActivityCrearRutinas.this,"Error al añadir Rutina a Mis Rutinas", Toast.LENGTH_SHORT).show();
                    //Eliminar la rutina de la base de datos ipsofacto
                }
            }
        });
    }



}