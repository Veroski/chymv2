package com.example.chymv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chymv2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    EditText signInUsername, signInPassword;
    Button signInBtn, signUpRedirectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInUsername = findViewById(R.id.signInUsername);
        signInPassword = findViewById(R.id.signInPassword);
        signInBtn = findViewById(R.id.loginBtn);
        signUpRedirectBtn = findViewById(R.id.signUpRedirectBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!valiadateUsername() | !valiadatePassword()){

                }
                else{
                    checkUser();
                }
            }
        });

        signUpRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUPActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean valiadateUsername(){
        String val = signInUsername.getText().toString();
        if (val.isEmpty()){
            signInUsername.setError("Username cannot be empty");
            return false;
        }
        else{
            signInUsername.setError(null);
            return true;
        }
    }

    public Boolean valiadatePassword(){
        String val = signInPassword.getText().toString();
        if (val.isEmpty()){
            signInPassword.setError("Password cannot be empty");
            return false;
        }
        else{
            signInPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = signInUsername.getText().toString().trim();
        String userPassword = signInPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    signInUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("contraseña").getValue(String.class);

                    System.out.println(passwordFromDB);
                    if (Objects.equals(passwordFromDB,userPassword)){
                        signInUsername.setError(null);
                        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        signInPassword.setError("Invalid Credentials");
                        signInPassword.requestFocus();
                    }
                }
                else{
                    signInUsername.setError("User does not exist");
                    signInUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}