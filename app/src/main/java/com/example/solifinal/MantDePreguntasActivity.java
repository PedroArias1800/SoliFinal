package com.example.solifinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.solifinal.Entidades.CVID_Modulo;
import com.example.solifinal.ListView.AdapterMantDeModulos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MantDePreguntasActivity extends AppCompatActivity {

    GridView gridv;
    List<CVID_Modulo>_modulos;
    int logos[] = {R.drawable.sin_img, R.drawable.sin_img, R.drawable.sin_img, R.drawable.sin_img,
    R.drawable.sin_img, R.drawable.sin_img};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mant_preguntas);
        gridv = (GridView) findViewById(R.id.gv); // init GridView
        // Crea un objeto de CustomAdapter y configura el Adaptador en GirdView
        AdapterMantDeModulos adapter = new AdapterMantDeModulos(getApplicationContext(), logos);
        gridv.setAdapter(adapter);

        ClickModulo();
    }

    private void ClickModulo() {
        gridv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = _modulos.get(position).getNombre();
                int moduloId = Integer.parseInt(_modulos.get(position).getId());
                Intent i = new Intent(getApplicationContext(),ModuloActivity.class);
                i.putExtra("moduloNom",nombre);
                i.putExtra("moduloID",moduloId);
                startActivity(i);
            }
        });
    }
    /*
    private void LoadListview() {
        Call<List<Juego>> response = ApiService.getApiService().getAllJuegos();
        response.enqueue(new Callback<List<Juego>>() {
            @Override
            public void onResponse(Call<List<Juego>> call, Response<List<Juego>> response) {
                if (response.isSuccessful()){
                    List<Juego> juegos = response.body();
                    _modulos = juegos;
                    JuegosListViewApdater adapter = new JuegosListViewApdater(getApplicationContext(),juegos);
                    lstJuegos.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Juego>> call, Throwable t) {
                int x = 1;
            }
        });

    }*/

}
