package com.example.pmdm_ejercicionavidadedusolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pmdm_ejercicionavidadedusolution.adapters.PersonajesAdapter;
import com.example.pmdm_ejercicionavidadedusolution.conexiones.ApiConexiones;
import com.example.pmdm_ejercicionavidadedusolution.conexiones.RetrofitObject;
import com.example.pmdm_ejercicionavidadedusolution.modelos.Personaje;
import com.example.pmdm_ejercicionavidadedusolution.modelos.Respuesta;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Recycler
    private RecyclerView recyclerView;
    private PersonajesAdapter adapter;
    private RecyclerView.LayoutManager lm;

    private List<Personaje> personajes;
    private Respuesta respuesta; //para saber los datos de lo traido la última vez (totalcount, page...)

    private ApiConexiones api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.contenedor);
        personajes = new ArrayList<>();
        adapter = new PersonajesAdapter(personajes, R.layout.personaje_view_holder, this);
        lm= new GridLayoutManager(this, 2); //para que salgan dos columnas
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(lm);

        api = RetrofitObject.getConnection().create(ApiConexiones.class);

        cargaInicialDatos();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(!recyclerView.canScrollVertically(1)){ //1 hacia abajo, -1 hacia arriba
                    if(respuesta != null && respuesta.getNextPage() != null){ //si ya he cargado una vez los datos y la pagina siguiente no sea nulo porque significara que es el final
                        String url= respuesta.getNextPage();
                        String page= url.split("=")[1]; //coge la url y la divide por el =, cogiendo la posicion que se encuentra en el 1 (el numero)
                        cargarMasPaginas(page);
                    }
                }



            }
        });

    }

    private void cargarMasPaginas(String page) {
        Call<Respuesta> newPage= api.getPage(page);

        newPage.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.code() == HttpsURLConnection.HTTP_OK){
                    //queremos mantener los datos anteriores asi que el adapter no podrá empezar a 0, sino al tamaño anterior.
                    int tam= personajes.size();

                    respuesta = response.body();
                    personajes.addAll(respuesta.getData());
                    adapter.notifyItemRangeInserted(tam, respuesta.getCount());
                    Toast.makeText(MainActivity.this, "Cargada Página: "+page, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {

            }
        });

    }

    private void cargaInicialDatos() {

        Call<Respuesta> doGetCarga = api.getPersonajes();

        doGetCarga.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.code()== HttpsURLConnection.HTTP_OK){
                    respuesta = response.body(); //aqui guardamos TODOS los datos de tipo Respuesta
                    personajes.addAll(respuesta.getData());
                    adapter.notifyItemRangeInserted(0, respuesta.getCount());
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {

            }
        });
    }
}