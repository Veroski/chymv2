package com.example.chymv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chymv2.R;
import com.example.chymv2.model.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private EditText signInUsername, signInPassword;
    private Button signInBtn, signUpRedirectBtn, googleRedirectBtn;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInUsername = findViewById(R.id.signInUsername);
        signInPassword = findViewById(R.id.signInPassword);
        signInBtn = findViewById(R.id.loginBtn);
        signUpRedirectBtn = findViewById(R.id.signUpRedirectBtn);
        googleRedirectBtn = findViewById(R.id.googleRedirectBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
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

        googleRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
            }
        });
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK){
                Intent intent = result.getData();
                Task<GoogleSignInAccount> task  = GoogleSignIn.getSignedInAccountFromIntent(intent);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);

                    assert account != null;
                    AutentificadorFirebase(account.getIdToken());
                }
                catch(ApiException e){
                    //Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

    private void AutentificadorFirebase(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        firebaseAuth.signInWithCredential(credential);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    if (task.getResult().getAdditionalUserInfo().isNewUser()){
                        String username = user.getUid();
                        String correo = user.getEmail();
                        String nombre = user.getDisplayName();
                        Usuario usuario = new Usuario(username,correo,"",nombre);
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference reference = firebaseDatabase.getReference("users");
                        reference.child(username).setValue(usuario);
                    }
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                }
                else{
                }
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
                        finish();
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