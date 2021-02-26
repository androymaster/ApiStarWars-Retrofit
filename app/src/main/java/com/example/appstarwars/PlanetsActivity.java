package com.example.appstarwars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appstarwars.Interface.JsonPlaceHolderApi;
import com.example.appstarwars.model.People;
import com.example.appstarwars.model.Planets;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanetsActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPlanets();

    }

    private void getPlanets() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Planets> call = jsonPlaceHolderApi.getPlanets();

        call.enqueue(new retrofit2.Callback<Planets>() {
            @Override
            public void onResponse(Call<Planets> call, Response<Planets> response) {
                if (!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: " + response.code());
                    return;
                }

                List<Planets> planetsList = Collections.singletonList(response.body());

                for (Planets planets: planetsList){
                    String content = "";
                    content += "Nombre: " + planets.getName() + "\n";
                    content += "Periodo de Rotación: " + planets.getRotationPeriod() + "\n";
                    content += "Periodo Orbital: " + planets.getOrbitalPeriod() + "\n";
                    content += "Diametro: " + planets.getDiameter() + "\n";
                    content += "Clima: " + planets.getClimate()+ "\n";
                    content += "Gravedad: " + planets.getGravity() + "\n";
                    content += "Superficie: " + planets.getTerrain() + "\n";
                    content += "Superficie de Agua: " + planets.getSurfaceWater() + "\n";
                    content += "Poblacion: " + planets.getPopulation() + "\n\n";
                    mJsonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<Planets> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });
    }
}