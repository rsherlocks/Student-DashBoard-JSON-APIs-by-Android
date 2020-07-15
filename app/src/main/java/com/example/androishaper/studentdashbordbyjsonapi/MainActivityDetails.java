package com.example.androishaper.studentdashbordbyjsonapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityDetails extends AppCompatActivity {
    TextView textViewName,textViewRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);
        textViewName=findViewById(R.id.textViewName);
        textViewRole=findViewById(R.id.textViewRole);
        Toolbar toolbar=findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String role=intent.getStringExtra("role");

        textViewName.setText(name);
        textViewRole.setText(role);

    }
}