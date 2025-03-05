package com.spp.shitalsurgicalco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserName extends AppCompatActivity {

    EditText name ,  phone;
    Button btn;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_name);

        name = findViewById(R.id.username_name);
        phone = findViewById(R.id.username_number);
        btn = findViewById(R.id.username_btn);

        sp = getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserName.this , GetEmail.class);

                String Username = name.getText().toString().trim();
                String Userphone = phone.getText().toString().trim();

                if(Username.isEmpty()){
                    Toast.makeText(UserName.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if(Userphone.isEmpty()){
                    Toast.makeText(UserName.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();

                }
                else {

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", Username);
                    editor.putString("userphone", Userphone);
                    editor.commit();


                    startActivity(i);
                }
            }
        });



    }
}