package com.spp.shitalsurgicalco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_account extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;
    FirebaseAuth authh;
    member Member;
    SharedPreferences sp;


    EditText new_email , new_name , new_address , new_password;
    Button new_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_account);

        new_email = findViewById(R.id.newemail);
        new_name = findViewById(R.id.newname);
        database = FirebaseDatabase.getInstance();

        new_address = findViewById(R.id.newaddress);
        new_password = findViewById(R.id.newpassword);
        new_btn = findViewById(R.id.newbtn);
        authh = FirebaseAuth.getInstance();
        Member = new member();
        sp = getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);

        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = new_name.getText().toString().trim();
                String Email = new_email.getText().toString().trim();
                String Password = new_password.getText().toString().trim();
                String Address = new_address.getText().toString().trim();
                String editEmail = Email+"@customdomain.com";


                if(Email.isEmpty()){

                    Toast.makeText(new_account.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if (Password.isEmpty()){
                    Toast.makeText(new_account.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(Name.isEmpty()){
                    Toast.makeText(new_account.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                } else if (Address.isEmpty()) {
                    Toast.makeText(new_account.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                }
                else {


                    authh.createUserWithEmailAndPassword(editEmail, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Intent intent = new Intent(new_account.this, UserName.class);
//                        intent.putExtra("name" , Name);
//                        intent.putExtra("email" , Email);
//                        intent.putExtra("address" , Address);

                            DatabaseReference myRef = database.getReference().child("Emails").child(Email);


                            Member.setName(Name);
                            Member.setAddress(Address);

                            myRef.push().setValue(Member);


                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("email",Email);
                            editor.commit();

                            startActivity(intent);

                        }
                    });
                }
            }
        });


    }

}