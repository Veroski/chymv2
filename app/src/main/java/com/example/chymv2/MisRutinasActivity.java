package com.example.chymv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.chymv2.adapters.RoutineListAdapter;
import com.example.chymv2.model.Rutina;
import com.example.chymv2.sources.InitializeData;
import com.example.chymv2.view.ExerciceFragment;
import com.example.chymv2.view.MainActivity;
import com.example.chymv2.view.ProfileFragment;
import com.example.chymv2.view.RoutineDescriptionActivity;
import com.example.chymv2.view.RoutineMenuFragment;
import com.example.chymv2.viewmodel.RoutineViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class MisRutinasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private RoutineListAdapter routineListAdapter;
    private RoutineViewModel routineViewModel;
    private androidx.appcompat.widget.SearchView routineSearch;
    private static InitializeData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_rutinas);

        NavigationBarView navigation = findViewById(R.id.bottom_navigation_misRutinas);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        //assert getSupportActionBar() != null;
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = new InitializeData(this);
        routineViewModel = new RoutineViewModel(this);
        initlistaRutinas();
        initListenerRoutines();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    private final NavigationBarView.OnItemSelectedListener mOnNavigationItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.exercices_fragment:
                    startActivity(new Intent(MisRutinasActivity.this, MainActivity.class).putExtra("parametro",2));
                    finish();
                    return true;
                case R.id.profile_fragment:
                    startActivity(new Intent(MisRutinasActivity.this, MainActivity.class).putExtra("parametro",3));
                    finish();
                    return true;
                case R.id.routine_fragment:
                    startActivity(new Intent(MisRutinasActivity.this, MainActivity.class).putExtra("parametro",1));
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
        routineSearch = findViewById(R.id.routineSearch);

        recyclerView = findViewById(R.id.routineListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(routineListAdapter);
    }
    private void initListenerRoutines(){
        routineSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        routineListAdapter.filter(s);
        routineListAdapter.notifyDataSetChanged();
        return false;
    }
    public void moveToRoutine(Rutina item){
        Intent intent = new Intent(this, RoutineDescriptionActivity.class);
        intent.putExtra("Rutina", item);
        startActivity(intent);
    }


}