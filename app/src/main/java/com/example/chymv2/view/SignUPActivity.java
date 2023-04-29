package com.example.chymv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chymv2.R;
import com.example.chymv2.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUPActivity extends AppCompatActivity {
    Boolean existEmail = false;
    Boolean existUsername = false;
    EditText signUpName, signUpEmail, signUpPassword, signUpUsername;
    TextView loginRedirectText;
    Button signUpButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpName = findViewById(R.id.singUpName);
        signUpUsername = findViewById(R.id.singUpUsername);
        signUpEmail = findViewById(R.id.singUpEmail);
        signUpPassword = findViewById(R.id.singUpPassword);
        signUpButton = findViewById(R.id.singInRedirectBtn);
        loginRedirectText = findViewById(R.id.signInRedirectText);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signUpName.getText().toString();
                String email = signUpEmail.getText().toString();
                String password = signUpPassword.getText().toString();
                String username = signUpUsername.getText().toString();
                String userEmail = signUpEmail.getText().toString().trim();
                String userUsername = signUpUsername.getText().toString().trim();

                createUser(userUsername,userEmail,username,name,email,password,reference);
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUPActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public Boolean validateName(String name){
        if(name.isEmpty()){
            signUpName.setError("Can't be empty");
            return false;
        }
        return true;
    }
    public Boolean validateEmail(String email){
        if(email.isEmpty()){
            signUpEmail.setError("Can't be empty");
            return false;
        }
        return true;
    }
    public Boolean validateUsername(String username){
        if(username.isEmpty()){
            signUpUsername.setError("Can't be empty");
            return false;
        }
        return true;
    }
    public Boolean validatePassword(String password){
        if(password.isEmpty()){
            signUpPassword.setError("Can't be empty");
            return false;
        }
        else if(password.length() < 6){
            signUpPassword.setError("Min lenght is 6 characters");
            return false;
        }
        return true;
    }

    public void createUser(String userUsername, String userEmail, String username, String name, String email,
                           String password, DatabaseReference reference){

        checkEmailDB(userEmail, reference);
        Usuario user = new Usuario(username,email,password,name);
        checkUsernameDB(user,userUsername,username,name,email, password,reference);
    }

    public void checkUsernameDB(Usuario user, String userUsername, String username, String name, String email,
                                String password, DatabaseReference reference){
        Query checkUserDatabase2 = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    existUsername = false;
                    if(!existEmail && validateUsername(username) && validateEmail(email) && validatePassword(password) && validateName(name)){

                        reference.child(username).setValue(user);
                        Toast.makeText(SignUPActivity.this, "You have sing up successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUPActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    signUpUsername.setError("Username alredy exist");
                    signUpUsername.requestFocus();
                    existUsername = true;

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void checkEmailDB(String userEmail, DatabaseReference reference){
        Query checkUserDatabase = reference.orderByChild("correo").equalTo(userEmail);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    existEmail = false;
                }
                else{
                    signUpEmail.setError("Email used to another account");
                    signUpEmail.requestFocus();
                    existEmail = true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}