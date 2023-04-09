package com.mad92.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        EditText nameController = findViewById(R.id.name);
        EditText rollController = findViewById(R.id.roll);
        EditText sectionController = findViewById(R.id.section);
        EditText topicController = findViewById(R.id.topic);

        Button add = findViewById(R.id.add);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameController.toString();
                String roll = rollController.toString();
                String section = sectionController.toString();
                String topic = topicController.toString();

                HashMap<String,Object> userData = new HashMap<>();
                userData.put("name",name);
                userData.put("roll",roll);
                userData.put("section",section);
                userData.put("topic",topic);

                database.child("Users")
                        .child(name)
                        .setValue(userData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(addData.this,"DATA ADDED",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addData.this,"FAILED TO ADD DATA",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}