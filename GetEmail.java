package com.spp.shitalsurgicalco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetEmail extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference database;
    GetEmailAdapter myAdapter;
    ArrayList<User> list;
    FirebaseAuth auth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_email);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myuserpref" , Context.MODE_PRIVATE);

//        String Name = sp.getString("hosname" , "");
//        String Address = sp.getString("hosadd" , "");
          String Email = sp.getString("email" , "");
          String editEail = Email.replace(".","");
//        String Username = sp.getString("username","");
//        String Userphone = sp.getString("userphone","");


        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Emails").child(Email);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        list = new ArrayList<>();
        myAdapter = new GetEmailAdapter(this,list);
        recyclerView.setAdapter(myAdapter);


        SharedPreferences.Editor editor = sp.edit();
        editor.putString("hosname" , myAdapter.getName());
        editor.putString("hosadd" , myAdapter.getAddress());
        editor.commit();


//
//        Intent i = new Intent(GetEmail.this , SecondActivity.class);
//        startActivity(i);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Intent i = new Intent(GetEmail.this , MainActivity.class);
                startActivity(i);
                editor.clear();
                editor.commit();

                auth.signOut();

                Toast.makeText(GetEmail.this, ""+error, Toast.LENGTH_SHORT).show();



            }
        });



    }
}