package com.example.pmdm_ejercicionavidadedusolution.conexiones;

import com.example.pmdm_ejercicionavidadedusolution.modelos.Personaje;
import com.example.pmdm_ejercicionavidadedusolution.modelos.Respuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {

    //Descarga de datos inicial
    @GET("/characters")
    Call<Respuesta> getPersonajes();


    //Descarga de una pagina en concreto
    @GET("/characters?")
    Call<Respuesta> getPage(@Query("page") String page);

    //Descarga de un personaje
    @GET("/characters/{idPersonaje}")
    Call<Personaje> getPersonaje(@Path("idPersonaje") String id);



}
