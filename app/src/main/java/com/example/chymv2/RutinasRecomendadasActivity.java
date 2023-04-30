package com.example.chymv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.chymv2.view.ExerciceFragment;
import com.example.chymv2.view.MainActivity;
import com.example.chymv2.view.ProfileFragment;
import com.example.chymv2.view.RoutineMenuFragment;
import com.google.android.material.navigation.NavigationBarView;

public class RutinasRecomendadasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas_recomendadas);

        NavigationBarView navigation = findViewById(R.id.bottom_navigation_rutinasRecomendadas);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    startActivity(new Intent(RutinasRecomendadasActivity.this, MainActivity.class).putExtra("parametro",2));
                    finish();
                    return true;
                case R.id.profile_fragment:
                    startActivity(new Intent(RutinasRecomendadasActivity.this, MainActivity.class).putExtra("parametro",3));
                    finish();
                    return true;
                case R.id.routine_fragment:
                    startActivity(new Intent(RutinasRecomendadasActivity.this, MainActivity.class).putExtra("parametro",1));
                    finish();
                    return true;
            }
            return false;
        }
    };

}