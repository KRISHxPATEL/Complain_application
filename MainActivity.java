package com.spp.shitalsurgicalco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    EditText password ,email , name , address;
    String Email , Password;
    Button btn;
    FirebaseDatabase database;
    DatabaseReference myRefmain ;
    SharedPreferences sp;
    TextView new_account;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.btn);
        new_account = findViewById(R.id.new_account);

        auth = FirebaseAuth.getInstance();
        sp = getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);

        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , new_account.class);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = email.getText().toString().trim();
                Password = password.getText().toString().trim();


                if(Email.isEmpty()){

                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(Password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    signin();
                }
            }
        });


    }

    private void signin() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String editEmail = Email+"@customdomain.com";
        auth.signInWithEmailAndPassword(editEmail , Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {




                Intent i = new Intent(MainActivity.this , UserName.class);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email" , Email);
                editor.commit();


                startActivity(i);
                Toast.makeText(MainActivity.this, "Log-IN Successful", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Log-IN Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}