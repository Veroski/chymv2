package com.example.chymv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chymv2.R;
import com.example.chymv2.adapters.ExerciceListAdapter;
import com.example.chymv2.model.ListExercice;
import com.example.chymv2.model.Rutina;
import com.example.chymv2.sources.InitializeData;
import com.example.chymv2.viewmodel.ExercicesViewModel;
import com.example.chymv2.viewmodel.RoutineDescriptionViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ActivityRoutineDescription extends AppCompatActivity {
    CollapsingToolbarLayout routineCollapsingToolbarLayout;
    RecyclerView rvRoutineExercices;
    TextView tvRoutineName;
    InitializeData data;
    RoutineDescriptionViewModel routineDescriptionViewModel;
    ExerciceListAdapter exerciceListAdapter;
    Rutina element;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_description);




        element = (Rutina) getIntent().getSerializableExtra("Rutina");
        routineDescriptionViewModel = new RoutineDescriptionViewModel(element);

        routineCollapsingToolbarLayout = findViewById(R.id.collapsingToolBarRoutine);
        rvRoutineExercices = findViewById(R.id.rvRoutineExercices);
        tvRoutineName = findViewById(R.id.tvRoutineName);


        initlistaEjercicios();
        routineCollapsingToolbarLayout.setTitle(element.getNombre());
        tvRoutineName.setText(element.getNombre());
        /*
        tvRoutineTitle = findViewById(R.id.tvRoutineTitle);
        tvRoutineDescription = findViewById(R.id.tvRoutineDescription);
        tvRoutineStatus = findViewById(R.id.tvRoutineStatus);

        tvRoutineTitle.setText(element.getNombre());
        tvRoutineTitle.setTextColor(Color.parseColor(element.getColor()));

        tvRoutineDescription.setText(element.getEjercicios().get(1).getEjercicio());// Ejamplo 1- Cambiar

        tvRoutineStatus.setText(element.getNombre());// Ejamplo 2- Cambiar
        tvRoutineStatus.setTextColor(Color.RED);
        */

    }
    private void initlistaEjercicios() {
        exerciceListAdapter = new ExerciceListAdapter(routineDescriptionViewModel.getExercices().getValue(), this, new ExerciceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListExercice item) {
                moveToDescription(item);
            }
        });
        exerciceListAdapter.notifyDataSetChanged();
        rvRoutineExercices.setLayoutManager(new LinearLayoutManager(this));
        rvRoutineExercices.setHasFixedSize(true);
        rvRoutineExercices.setAdapter(exerciceListAdapter);
    }
    public void moveToDescription(ListExercice item){
        Intent intent = new Intent(this, ActivityExerciceDescription.class);
        intent.putExtra("ListExercice", item);
        startActivity(intent);
    }

}
