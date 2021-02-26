package com.example.appstarwars.Interface;

import com.example.appstarwars.model.People;
import com.example.appstarwars.model.Planets;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("people/1")
    Call<People> getPeople();

    @GET("planets/1")
    Call<Planets> getPlanets();

}
