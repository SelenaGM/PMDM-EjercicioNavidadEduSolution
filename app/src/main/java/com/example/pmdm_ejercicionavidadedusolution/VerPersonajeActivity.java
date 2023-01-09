package com.example.pmdm_ejercicionavidadedusolution;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmdm_ejercicionavidadedusolution.conexiones.ApiConexiones;
import com.example.pmdm_ejercicionavidadedusolution.conexiones.RetrofitObject;
import com.example.pmdm_ejercicionavidadedusolution.modelos.Personaje;
import com.squareup.picasso.Picasso;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerPersonajeActivity extends AppCompatActivity {

    private ImageView imgPersonaje;
    private TextView lblNombre;
    private TextView lblFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personaje);

        imgPersonaje = findViewById(R.id.imgPersonajeVer);
        lblNombre = findViewById(R.id.lblNombreVer);
        lblFilm= findViewById(R.id.lblFilmsVer);


        if(getIntent().getExtras() != null && getIntent().getExtras().getString("ID")!=null){
            ApiConexiones api = RetrofitObject.getConnection().create(ApiConexiones.class);
            Call<Personaje> personajeCall = api.getPersonaje(getIntent().getExtras().getString("ID"));

            personajeCall.enqueue(new Callback<Personaje>() {
                @Override
                public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                    if(response.code() == HttpsURLConnection.HTTP_OK){
                        Personaje p = response.body();
                        lblNombre.setText(p.getName());
                        lblFilm.setText("");
                        for (String film: p.getFilms() ) {
                            lblFilm.setText(lblFilm.getText()+"\n"+film);
                        }
                        Picasso.get()
                                .load(p.getImageUrl())
                                .into(imgPersonaje);
                    }
                }

                @Override
                public void onFailure(Call<Personaje> call, Throwable t) {

                }
            });
        }
    }
}