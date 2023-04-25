package com.example.chymv2.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.chymv2.R;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ExerciceFragment exerciceFragment = new ExerciceFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    RoutineFragment routineFragment = new RoutineFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationBarView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(routineFragment);
    }

    private final NavigationBarView.OnItemSelectedListener mOnNavigationItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.exercices_fragment:
                    loadFragment(exerciceFragment);
                    return true;
                case R.id.profile_fragment:
                    loadFragment(profileFragment);
                    return true;
                case R.id.routine_fragment:
                    loadFragment(routineFragment);
                    return true;
            }
            return false;
        }
    };
    public void loadFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout_MainActivity,fragment);
        transaction.commit();
    }
}