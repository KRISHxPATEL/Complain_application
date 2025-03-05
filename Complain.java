package com.spp.shitalsurgicalco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;


public class Complain extends AppCompatActivity {


    TextView Reg_name , Reg_phone  , AMC_ex , date_reg , hosname , hosadd , email;
    Button reg_btn;
    EditText full_disp;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    member Member;
    Memberpartner memberpartner;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        Reg_name = findViewById(R.id.reg_name);
        Reg_phone = findViewById(R.id.reg_number);
        full_disp = findViewById(R.id.discription);
        hosname = findViewById(R.id.hosname);
        email = findViewById(R.id.Email);
        AMC_ex = findViewById(R.id.AMC_expiring);
        hosadd = findViewById(R.id.hosadd);
        String amcdummyexp = "NA";
        reg_btn = findViewById(R.id.reg_btn);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);
        String Name = sp.getString("hosname" , "");
        String Address = sp.getString("hosadd" , "");
        String Email = sp.getString("email" , "");
        String Username = sp.getString("username","");
        String Userphone = sp.getString("userphone","");



//        Bundle bundle = getIntent().getExtras();
//        String Name = bundle.getString("name");
//        String Address = bundle.getString("address");
//        String Email = bundle.getString("email");


        String Calendars = DateFormat.getDateInstance().format(calendar.getTime());
        Member = new member();
        memberpartner = new Memberpartner();


        Reg_name.setText("Your Name:-"+Username);
        Reg_phone.setText("Your Phone No.:-"+Userphone);
        hosname.setText("Hospital Name :-"+Name);
        hosadd.setText("Hospital Address :-"+Address);
        AMC_ex.setText("AMC expiring :-"+amcdummyexp);
        email.setText("Hospital Phone :-"+Email);


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference myRef = database.getReference().child("Complain").child(Name).child(Calendars);

                DatabaseReference myReff = database.getReference().child("data").child(Name);
                DatabaseReference myRefff = database.getReference().child("partner").child("complain");
                long timestamp = System.currentTimeMillis();

                Member.setName(Name);
                Member.setAddress(Address);
                Member.setEmail(Email);
                Member.setReg_name(Username);
                Member.setReg_phone(Userphone);
                Member.setFulldisp(full_disp.getText().toString());
                Member.setAMC_expireing(amcdummyexp);
                Member.setReg_Date(Calendars);
                Member.setTaken("Not Accepted");
                Member.setTakenphone("Not Accepted");

                memberpartner.setName(Name);
                memberpartner.setAddress(Address);
                memberpartner.setEmail(Email);
                memberpartner.setReg_name(Username);
                memberpartner.setReg_phone(Userphone);
                memberpartner.setFulldisp(full_disp.getText().toString());
                memberpartner.setAMC_expireing(amcdummyexp);
                memberpartner.setReg_Date(Calendars);
                memberpartner.setStatus("Pending");






                Intent i = new Intent(Complain.this , Confirmation.class);
                i.putExtra("name" , Name);
                i.putExtra("address" , Address);
                i.putExtra("email" , Email);
                i.putExtra("date" , Calendars);
                startActivity(i);

                myRef.push().setValue(Member);

                myRefff.push().setValue(memberpartner);

                myReff.child("1").setValue(Member);



            }
        });

    }
}