package com.spp.shitalsurgicalco;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;


public class AMC_purchase extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    TextView tv_name , tv_address , tv_email;
    Button btn_request;
    member Member;
    Calendar calendar = Calendar.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amc_purchase);


        Member = new member();


        SharedPreferences sp = getApplicationContext().getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);
        String Name = sp.getString("hosname" , "");
        String Address = sp.getString("hosadd" , "");
        String Email = sp.getString("email" , "");
        String Username = sp.getString("username","");
        String Userphone = sp.getString("userphone","");

        tv_name = findViewById(R.id.tv_name);

        btn_request = findViewById(R.id.btn_request);
        tv_address = findViewById(R.id.tv_address);
        tv_email = findViewById(R.id.tv_email);

        tv_name.setText("Hospital Name : "+Name);
        tv_address.setText("Hospital Address : "+Address);
        tv_email.setText("Hospital Phone : "+Email);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.payment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        String Calendars = DateFormat.getDateInstance().format(calendar.getTime());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();

                Member.setName(Name);
                Member.setAddress(Address);
                Member.setEmail(Email);
                Member.setNoMachine(text);
                Member.setReg_Date(Calendars);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference myRef = database.getReference().child("AMC_Request").child(Name);
                DatabaseReference myRefff = database.getReference().child("partner").child("AMC");


                myRef.push().setValue(Member);
                myRefff.push().setValue(Member);

                Intent intent = new Intent(AMC_purchase.this , ListAMC.class);
                startActivity(intent);

                Toast.makeText(AMC_purchase.this, "done", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), ""+text, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}