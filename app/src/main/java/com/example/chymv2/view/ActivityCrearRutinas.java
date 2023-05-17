package com.example.chymv2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chymv2.R;
import com.example.chymv2.adapters.ExerciceListAdapter;
import com.example.chymv2.model.ListExercice;
import com.example.chymv2.model.Rutina;
import com.example.chymv2.viewmodel.CrearRutinasViewModel;
import com.example.chymv2.viewmodel.ExercicesViewModel;
import com.example.chymv2.viewmodel.RoutineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ActivityCrearRutinas extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private Button returnMain_crearRutinas_btn, btnCrearRutina;
    private TextInputEditText tvColorRoutine, tvNameRoutine,tvRoutineType, tvExerciceListRoutine;
    private NavigationBarView navigation;
    private RoutineViewModel routineViewModel;
    private String color, nombre,routineType,listaEjs, idList;
    private FloatingActionButton fabAddExercices;
    private Dialog myDialog;
    private ExerciceListAdapter exerciceListAdapterPopUp;
    private ExerciceListAdapter exerciceListAdapter;
    private ExercicesViewModel exercicesViewModelPopUp;
    private CrearRutinasViewModel crearRutinasViewModel;
    private RecyclerView rvCrearRutinas;

    //POPUP
    private ImageView closePopUp;
    private androidx.appcompat.widget.SearchView searchPopUp;
    private RecyclerView recyclerPopUp;
    private Spinner spinnerPopUp;
    private Button btnPopUp;
    private List<ListExercice> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rutinas);
        routineViewModel = new RoutineViewModel(this,2);
        navigation = findViewById(R.id.bottom_navigation_crearRutinas);

        tvColorRoutine = findViewById(R.id.tvColorRoutine);
        tvRoutineType = findViewById(R.id.tvTypeRoutine);
        tvNameRoutine = findViewById(R.id.tvNameRoutine);
        fabAddExercices = findViewById(R.id.fabCrearRutinas);
        rvCrearRutinas = findViewById(R.id.rvCrearRutinas);

        returnMain_crearRutinas_btn = findViewById(R.id.returnMain_crearRutinas_btn);
        btnCrearRutina = findViewById(R.id.btnRoutineCreate);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        myDialog = new Dialog(this);
        crearRutinasViewModel = new CrearRutinasViewModel(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        onClickListeners();
        listaEjerciciosSelec();

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
                //listaEjs = tvExerciceListRoutine.getText().toString();
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
        fabAddExercices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;
                showPopUpView();
            }
        });
    }

    public void listaEjerciciosSelec(){
        exerciceListAdapter = new ExerciceListAdapter(crearRutinasViewModel.getExercices().getValue(), this, new ExerciceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListExercice item) {

            }
        });
        rvCrearRutinas.setLayoutManager(new LinearLayoutManager(this));
        rvCrearRutinas.setHasFixedSize(true);
        rvCrearRutinas.setAdapter(exerciceListAdapter);
    }
/*
--------------------------------------------------------------------------
--------------------------------------------------------------------------
--------------------------------------------------------------------------

                            ACTIVITY POP UP

--------------------------------------------------------------------------
--------------------------------------------------------------------------
--------------------------------------------------------------------------
 */
    public void showPopUpView(){
        elements = new ArrayList<>();

        myDialog.setContentView(R.layout.activity_pop_up_crear_rutinas);
        myDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        exercicesViewModelPopUp = new ExercicesViewModel(myDialog.getContext());
        closePopUp = (ImageView) myDialog.findViewById(R.id.ivClosePopUp);
        searchPopUp = (androidx.appcompat.widget.SearchView) myDialog.findViewById(R.id.svPopUp);
        recyclerPopUp = (RecyclerView) myDialog.findViewById(R.id.rvPopUp);
        btnPopUp = (Button) myDialog.findViewById(R.id.btnPopUp);
        spinnerPopUp =(Spinner) myDialog.findViewById(R.id.spinnerPopUp);

        popUpOnClickListeners();
        searchPopUp.requestFocus();
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initListEjercicios();
        initListenerTeclado();
        myDialog.show();
    }

    public void popUpOnClickListeners(){
        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        spinnerPopUp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(myDialog.getContext(),"Seleccionado: " + adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                exerciceListAdapterPopUp.filterByType(adapterView.getItemAtPosition(i).toString());
                searchPopUp.setQuery("",true);
                exerciceListAdapterPopUp.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(ListExercice i: elements){
                    ListExercice newExercice = i;
                    crearRutinasViewModel.addNewValue(newExercice);
                    exerciceListAdapter.notifyDataSetChanged();
                }
                myDialog.dismiss();
            }
        });
    }

    public void initListEjercicios(){
        exerciceListAdapterPopUp = new ExerciceListAdapter(exercicesViewModelPopUp.getExercices().getValue(), myDialog.getContext(), new ExerciceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListExercice item) {
                elements.add(item);
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(myDialog.getContext(), R.array.combo_musculos, android.R.layout.simple_spinner_item);
        spinnerPopUp.setAdapter(adapter);
        recyclerPopUp.setLayoutManager(new LinearLayoutManager(myDialog.getContext()));
        recyclerPopUp.setHasFixedSize(true);
        recyclerPopUp.setAdapter(exerciceListAdapterPopUp);
    }

    private void initListenerTeclado(){
        searchPopUp.setOnQueryTextListener(this);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        exerciceListAdapterPopUp.filter(newText);
        exerciceListAdapterPopUp.notifyDataSetChanged();
        return false;
    }
}