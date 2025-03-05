package com.spp.shitalsurgicalco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SharedPprefrence extends AppCompatActivity {

    FirebaseAuth auth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_pprefrence);

        auth = FirebaseAuth.getInstance();



        SharedPreferences sp = getApplicationContext().getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);

        String Name = sp.getString("hosname" , "");
        String Email = sp.getString("email","");
        String Username = sp.getString("username","");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                            if(Email.isEmpty()){

                                Intent i = new Intent(SharedPprefrence.this , MainActivity.class);
                                startActivity(i);
                            }
                            else if(Username.isEmpty()) {


                                Intent intent = new Intent(SharedPprefrence.this, UserName.class);
                                startActivity(intent);
                            }
                            else {


                                Intent intent = new Intent(SharedPprefrence.this , GetEmail.class);
                                startActivity(intent);

                            }

                        }

                });
            }
        }, 5000);



    }

}