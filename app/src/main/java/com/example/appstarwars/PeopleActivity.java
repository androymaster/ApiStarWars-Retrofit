package com.example.appstarwars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.widget.TextView;

import com.example.appstarwars.Interface.JsonPlaceHolderApi;
import com.example.appstarwars.model.People;
import com.example.appstarwars.model.Planets;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPeople();
    }

    private void getPeople(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<People> call = jsonPlaceHolderApi.getPeople();

        call.enqueue(new retrofit2.Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                if (!response.isSuccessful()){
                   mJsonTxtView.setText("Codigo: " + response.code());
                   return;
                }

                List<People> peopleList = Collections.singletonList(response.body());

                for (People people: peopleList){
                     String content = "";
                     content += "Nombre: " + people.getName() + "\n";
                     content += "Altura: " + people.getHeight() + "\n";
                     content += "Masa: " + people.getMass() + "\n";
                     content += "Color de Cabello: " + people.getHairColor() + "\n";
                     content += "SkinColor: " + people.getSkinColor() + "\n";
                     content += "Color de Ojos: " + people.getEyeColor() + "\n";
                     content += "Cumpleaños: " + people.getBirthYear() + "\n";
                     content += "Genero: " + people.getGender() + "\n\n";
                     mJsonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                   mJsonTxtView.setText(t.getMessage());
            }
        });
    }
}