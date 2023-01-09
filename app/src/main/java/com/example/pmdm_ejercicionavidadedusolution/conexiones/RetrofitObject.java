package com.example.pmdm_ejercicionavidadedusolution.conexiones;

import com.example.pmdm_ejercicionavidadedusolution.configuraciones.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    public static Retrofit getConnection(){
        return new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
