package com.spp.shitalsurgicalco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {

    TextView name , add , email , username , userphone;
    Button ptohome;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.namep);
        add = findViewById(R.id.addp);
        email = findViewById(R.id.emailp);
        username = findViewById(R.id.usernamep);
        userphone = findViewById(R.id.userphonep);
        ptohome = findViewById(R.id.ptohome);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);
        String Name = sp.getString("hosname" , "");
        String Address = sp.getString("hosadd" , "");
        String Email = sp.getString("email" , "");
        String Username = sp.getString("username","");
        String Userphone = sp.getString("userphone","");


        name.setText("Hospital Name : "+Name);
        add.setText("Address : "+Address);
        email.setText("Hospital Phone : "+Email);
        username.setText("Your Name : " + Username);
        userphone.setText("Your Phone : " + Userphone);

        ptohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this , SecondActivity.class);
                i.putExtra("hosname",Name);
                i.putExtra("hosadd",Address);
                startActivity(i);
            }
        });



    }
}