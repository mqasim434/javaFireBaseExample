package com.mad92.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        EditText emailController = findViewById(R.id.emailLoginController);
        EditText passController = findViewById(R.id.passwordLoginController);
        Button signin = findViewById(R.id.btnSignIn);
        TextView dont = findViewById(R.id.btnDontHave);

        auth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailController.getText().toString();
                String pass = passController.getText().toString();

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent iNext;
                            iNext = new Intent(Signin.this,Home.class);
                            startActivity(iNext);
                        }
                        else{
                            Toast.makeText(Signin.this,"Failed to sign ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this,Signup.class));
            }
        });
    }
}