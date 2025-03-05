package com.spp.shitalsurgicalco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        String Name = bundle.getString("name");
    }
}