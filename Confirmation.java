package com.spp.shitalsurgicalco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {

    TextView name , address , email , date;
    Button listbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Bundle bundle = getIntent().getExtras();
        String Name = bundle.getString("name");
        String Address = bundle.getString("address");
        String Email = bundle.getString("email");
        String Date = bundle.getString("date");

        name = findViewById(R.id.namec);
        address = findViewById(R.id.addressc);
        email = findViewById(R.id.emailc);
        date = findViewById(R.id.datec);
        listbtn = findViewById(R.id.listbtn);

        name.setText("Name : "+Name);
        address.setText("Address : "+Address);
        email.setText("Phone : "+Email);
        date.setText("Date : "+Date);

        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Confirmation.this , List_Complain.class);
                i.putExtra("name" , Name);
                i.putExtra("address" , Address);
                i.putExtra("email" , Email);
                startActivity(i);
            }
        });


    }
}