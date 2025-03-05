package com.spp.shitalsurgicalco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Spare_Part extends AppCompatActivity {

    TextView name , address , email , date , discription;
    EditText name_spare;
    Button spare_btn;
    member Member;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_part);

        Bundle bundle = getIntent().getExtras();
        String Date = bundle.getString("Date_S");
        String Discription = bundle.getString("Discription_S");

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);
        String Name = sp.getString("hosname" , "");
        String Address = sp.getString("hosadd" , "");
        String Email = sp.getString("email" , "");
        String Username = sp.getString("username","");
        String Userphone = sp.getString("userphone","");


        name_spare = findViewById(R.id.name_spare);
        spare_btn = findViewById(R.id.spare_btn);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        date = findViewById(R.id.date);
        discription = findViewById(R.id.discription);

        Member = new member();

        name.setText("Hospital Name : "+Name);
        address.setText("Hospital Address : "+Address);
        email.setText("Hospital Phone : "+Email);
        date.setText("Date : "+Date);

        
        spare_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference myRef = database.getReference().child("Complain").child(Name).child(Date).child("Spare Part");
                DatabaseReference myRefff = database.getReference().child("partner").child("sparepart");

               // DatabaseReference myreff = database.getReference("data").child(Name);


                Member.setSpare_part(name_spare.getText().toString());
                Member.setName(Name);
                myRef.push().setValue(Member);
               //info myreff.push().setValue(Member);
                myRefff.push().setValue(Member);

                Toast.makeText(Spare_Part.this, "Request Submitted", Toast.LENGTH_SHORT).show();

            }
        });



    }
}