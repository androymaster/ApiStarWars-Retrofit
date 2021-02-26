package com.example.appstarwars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPeople, btnPlanets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btnPeople = findViewById(R.id.btnPeople);
        btnPlanets = findViewById(R.id.btnPlanet);

        btnPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PeopleActivity.class);
                startActivity(i);
            }
        });

        btnPlanets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PlanetsActivity.class);
                startActivity(i);
            }
        });
    }
}