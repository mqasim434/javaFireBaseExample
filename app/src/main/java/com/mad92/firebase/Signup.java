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

import org.w3c.dom.Text;

public class Signup extends AppCompatActivity {

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText emailController = findViewById(R.id.emailController);
        EditText passwordController = findViewById(R.id.passwordController);
        EditText rePasswordController = findViewById(R.id.rePasswordController);
        Button signUp = findViewById(R.id.btnSignup);
        TextView already = findViewById(R.id.btnAleady);
        TextView text = findViewById(R.id.text);

        auth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email = emailController.getText().toString();
                String pass = passwordController.getText().toString();
                String rePass = rePasswordController.getText().toString();
                if( !email.contains("@")){
                    text.setText("Invalid Email");
                }
                else if(!(pass.equals(rePass))){
                    text.setText("password does not match");
                }
                else{
                    text.setText("");

                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent iNext;
                                iNext = new Intent(Signup.this,Signin.class);
                                startActivity(iNext);
                            }
                            else{
                                Toast.makeText(Signup.this,"Failed to sign up", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Signin.class));
            }
        });


    }
}