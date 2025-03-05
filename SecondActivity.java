package com.spp.shitalsurgicalco;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SecondActivity extends AppCompatActivity {


    TextView List_btn , AMC_puchase_btn  , detailAmcBtn , username;
    LinearLayout complain_btn , spare_part;
    ImageView logOutBtn , profilebtn;
    FirebaseAuth auth;
    DatabaseReference reference;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();
        String Name = bundle.getString("hosname");
        String Address = bundle.getString("hosadd");



        SharedPreferences sp = getApplicationContext().getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);


        String Email = sp.getString("email" , "");
        String Username = sp.getString("username","");
        String Userphone = sp.getString("userphone","");



        SharedPreferences.Editor editor = sp.edit();
        editor.putString("hosname", Name);
        editor.putString("hosadd" , Address);
        editor.commit();

        complain_btn = findViewById(R.id.complain_btn);
        List_btn = findViewById(R.id.complain_list_btn);
        detailAmcBtn = findViewById(R.id.Detail_AMC_btn);
        spare_part = findViewById(R.id.spare_btn);
        username = findViewById(R.id.helloText);
        profilebtn = findViewById(R.id.profilebtn);
        logOutBtn = findViewById(R.id.logoutbtn);


        username.setText("Hello "+Username);



        AMC_puchase_btn = findViewById(R.id.AMC_purchasing_btn);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this , MainActivity.class);
                startActivity(i);
                editor.clear();
                editor.commit();

                auth.signOut();
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(SecondActivity.this, profile.class);
                startActivity(i);

            }
        });

        complain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this , Complain.class);
                startActivity(i);

            }
        });
        List_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, List_Complain.class);
                startActivity(i);

            }
        });
        spare_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SecondActivity.this , List_Complain.class);

                startActivity(i);
            }
        });
        AMC_puchase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this , AMC_purchase.class);

                startActivity(i);
            }
        });
        detailAmcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(SecondActivity.this,ListAMC.class);

                startActivity(i);
            }
        });

    }
}