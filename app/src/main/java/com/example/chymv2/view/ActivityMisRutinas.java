package com.example.chymv2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.chymv2.R;
import com.example.chymv2.adapters.RoutineListAdapter;
import com.example.chymv2.model.Rutina;
import com.example.chymv2.sources.InitializeData;
import com.example.chymv2.viewmodel.RoutineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityMisRutinas extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RoutineListAdapter routineListAdapter;
    private RoutineViewModel routineViewModel;
    private Button returnMain_rutinasRecomendadas_btn;
    private FloatingActionButton fabMisRutinas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_rutinas);

        NavigationBarView navigation = findViewById(R.id.bottom_navigation_misRutinas);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        returnMain_rutinasRecomendadas_btn = findViewById(R.id.returnMain_misRutinas_btn);
        fabMisRutinas = findViewById(R.id.fabMisRutinas);
        returnMain_rutinasRecomendadas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //assert getSupportActionBar() != null;
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabMisRutinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMisRutinas.this, ActivityCrearRutinas.class));;
            }
        });
        routineViewModel = new RoutineViewModel(this);
        initlistaRutinas();

    }
    /*@Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }*/

    private final NavigationBarView.OnItemSelectedListener mOnNavigationItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.exercices_fragment:
                    startActivity(new Intent(ActivityMisRutinas.this, ActivityMain.class).putExtra("parametro",2));
                    finish();
                    return true;
                case R.id.profile_fragment:
                    startActivity(new Intent(ActivityMisRutinas.this, ActivityMain.class).putExtra("parametro",3));
                    finish();
                    return true;
                case R.id.routine_fragment:
                    startActivity(new Intent(ActivityMisRutinas.this, ActivityMain.class).putExtra("parametro",1));
                    finish();
                    return true;
            }
            return false;
        }
    };
    private void initlistaRutinas() {
        routineListAdapter = new RoutineListAdapter(routineViewModel.getRoutines().getValue(),this, new RoutineListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Rutina item) {
                moveToRoutine(item);
            }
        });
        routineListAdapter.notifyDataSetChanged();

        recyclerView = findViewById(R.id.routineListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(routineListAdapter);
    }
    public void moveToRoutine(Rutina item){
        Intent intent = new Intent(this, ActivityRoutineDescription.class);
        intent.putExtra("Rutina", item);
        startActivity(intent);
    }
}