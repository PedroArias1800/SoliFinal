package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.solifinal.ListView.TablaListViewAdapter;
import com.example.solifinal.Entidades.CVID_Tabla;
import com.example.solifinal.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PantallaRankingActivity extends AppCompatActivity {

    ListView lstTabla;
    int tipo;

    ImageView imgCargando;
    AnimationDrawable animationDrawable;
    MediaPlayer click, music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallaranking);

        imgCargando = (ImageView)findViewById(R.id.imgCargando);
        imgCargando.setBackgroundResource(R.drawable.cargando);
        imgCargando.setVisibility(View.VISIBLE);

        animationDrawable = (AnimationDrawable)imgCargando.getBackground();
        animationDrawable.start();

        click = MediaPlayer.create(this, R.raw.click);

        music = MediaPlayer.create(this, R.raw.born);
        music.start();

        InicializarControles();
        LoadListView(0);
    }

    private void LoadListView(int n) {
        Call<List<CVID_Tabla>> response = ApiService.getApiService().getAllTable(n);
        response.enqueue(new Callback<List<CVID_Tabla>>() {
            @Override
            public void onResponse(Call<List<CVID_Tabla>> call, Response<List<CVID_Tabla>> response) {
                if (response.isSuccessful()){
                    List<CVID_Tabla> table = response.body();
                    TablaListViewAdapter adapter = new TablaListViewAdapter(getApplicationContext(),table);
                    imgCargando.setVisibility(View.GONE);
                    lstTabla.setVisibility(View.VISIBLE);
                    lstTabla.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CVID_Tabla>> call, Throwable t) {

            }
        });
    }

    private void InicializarControles() {
        lstTabla = (ListView)findViewById(R.id.lstTabla);
    }

    public void General(View v){
        click.start();
        tipo=1;
        LoadListView(tipo);
    }

    public void Local(View v){
        click.start();
        tipo=0;
        LoadListView(tipo);
    }
}