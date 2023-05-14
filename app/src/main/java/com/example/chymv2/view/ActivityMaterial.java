package com.example.chymv2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;

import com.example.chymv2.R;
import com.example.chymv2.adapters.MaterialListAdapter;
import com.example.chymv2.model.CardMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityMaterial extends AppCompatActivity {

    List<CardMaterial> elements;
    RecyclerView materialRecyclerView ;
    MaterialListAdapter materialAdapter;

    CardMaterial cardMaterial;
    FirebaseAuth firebaseAuth;

    CheckBox materialCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);


        init();
    }
    public void init(){
        firebaseAuth = FirebaseAuth.getInstance();

        elements = new ArrayList<>();
        elements.add(new CardMaterial("Mancuerna", false));
        elements.add(new CardMaterial("Barra de Pesas", false));
        elements.add(new CardMaterial("Barra de Dominadas", false));
        elements.add(new CardMaterial("Maquinas", false));
        elements.add(new CardMaterial("Kettlebell", false));
        elements.add(new CardMaterial("Peso Corporal", false));

        materialAdapter = new MaterialListAdapter(elements, this, new MaterialListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CardMaterial item) {
                updateMaterial(item);
            }
        });
        materialAdapter.notifyDataSetChanged();
        materialRecyclerView = findViewById(R.id.rvMaterial);
        materialRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialRecyclerView.setHasFixedSize(true);
        materialRecyclerView.setAdapter(materialAdapter);
    }
    public void updateMaterial(CardMaterial item){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        cardMaterial = item;
        DatabaseReference BASE_DE_DATOS = FirebaseDatabase.getInstance().getReference("users");
        BASE_DE_DATOS.child(user.getUid()).child("materiales").child(item.getName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                }else{
                    String material = ""+snapshot.getValue();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference reference = firebaseDatabase.getReference("users");
                    reference.child(user.getUid()).child("materiales");


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(item.isStatus()){
            item.setStatus(false);
            System.out.println("desmarcada");
        }
        else{
            item.setStatus(true);
            System.out.println("marcada");
        }
    }

}