package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EscogerModulo extends AppCompatActivity {

    Button facil, medio, dificil, atras, empezar;
    int nivel=0;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_modulo);
        InicializarControles();
    }

    public void InicializarControles(){
        facil = (Button)findViewById(R.id.P_btn1);
        medio = (Button)findViewById(R.id.P_btn2);
        dificil = (Button)findViewById(R.id.P_btn3);
        atras = (Button)findViewById(R.id.P_Atras);
        empezar = (Button)findViewById(R.id.P_Empezar);

    }

    public void facil(View v){
        facil.setVisibility(View.GONE);
        medio.setVisibility(View.GONE);
        dificil.setVisibility(View.GONE);
        atras.setVisibility(View.VISIBLE);
        empezar.setVisibility(View.VISIBLE);

        nivel=1;
    }

    public void medio(View v){
        facil.setVisibility(View.GONE);
        medio.setVisibility(View.GONE);
        dificil.setVisibility(View.GONE);
        atras.setVisibility(View.VISIBLE);
        empezar.setVisibility(View.VISIBLE);

        nivel=2;
    }

    public void dificil(View v){
        facil.setVisibility(View.GONE);
        medio.setVisibility(View.GONE);
        dificil.setVisibility(View.GONE);
        atras.setVisibility(View.VISIBLE);
        empezar.setVisibility(View.VISIBLE);

        nivel=3;
    }

    public void atras(View v){
        facil.setVisibility(View.VISIBLE);
        medio.setVisibility(View.VISIBLE);
        dificil.setVisibility(View.VISIBLE);
        atras.setVisibility(View.GONE);
        empezar.setVisibility(View.GONE);
    }

    public void empezar(View v){
        i = new Intent(getApplicationContext(), PantallaJugar.class);
        i.putExtra("nivel", nivel);
        startActivity(i);
    }

    public void Utp(View view) {
        i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://utp.ac.pa/"));
        startActivity(i);
    }

    public void UtpFisc(View view) {
        i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fisc.utp.ac.pa/"));
        startActivity(i);
    }
}