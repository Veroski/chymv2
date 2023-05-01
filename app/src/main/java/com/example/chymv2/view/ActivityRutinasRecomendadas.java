package com.example.chymv2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.chymv2.R;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityRutinasRecomendadas extends AppCompatActivity {

    private Button returnMain_rutinasRecomendadas_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas_recomendadas);

        NavigationBarView navigation = findViewById(R.id.bottom_navigation_rutinasRecomendadas);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        returnMain_rutinasRecomendadas_btn = findViewById(R.id.returnMain_rutinasRecomendadas_btn);

        returnMain_rutinasRecomendadas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //assert getSupportActionBar() != null;
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    startActivity(new Intent(ActivityRutinasRecomendadas.this, ActivityMain.class).putExtra("parametro",2));
                    finish();
                    return true;
                case R.id.profile_fragment:
                    startActivity(new Intent(ActivityRutinasRecomendadas.this, ActivityMain.class).putExtra("parametro",3));
                    finish();
                    return true;
                case R.id.routine_fragment:
                    startActivity(new Intent(ActivityRutinasRecomendadas.this, ActivityMain.class).putExtra("parametro",1));
                    finish();
                    return true;
            }
            return false;
        }
    };

}