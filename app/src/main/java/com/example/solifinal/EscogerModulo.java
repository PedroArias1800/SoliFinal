package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EscogerModulo extends AppCompatActivity {

    Button facil, medio, dificil, atras, empezar;
    int nivel=0;
    Intent i;
    TextView introduccion;


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
        introduccion = (TextView)findViewById(R.id.Introducción);

    }

    public void facil(View v){
        facil.setVisibility(View.GONE);
        medio.setVisibility(View.GONE);
        dificil.setVisibility(View.GONE);
        atras.setVisibility(View.VISIBLE);
        empezar.setVisibility(View.VISIBLE);
        introduccion.setText("Nota: Toma en cuenta que si el tiempo llega a cero antes de que contestes no se te sumarán puntos...");

        nivel=1;
    }

    public void medio(View v){
        facil.setVisibility(View.GONE);
        medio.setVisibility(View.GONE);
        dificil.setVisibility(View.GONE);
        atras.setVisibility(View.VISIBLE);
        empezar.setVisibility(View.VISIBLE);
        introduccion.setText("Nota: Toma en cuenta que si el tiempo llega a cero antes de que contestes no se te sumarán puntos...");

        nivel=2;
    }

    public void dificil(View v){
        facil.setVisibility(View.GONE);
        medio.setVisibility(View.GONE);
        dificil.setVisibility(View.GONE);
        atras.setVisibility(View.VISIBLE);
        empezar.setVisibility(View.VISIBLE);
        introduccion.setText("Nota: Toma en cuenta que si el tiempo llega a cero antes de que contestes no se te sumarán puntos...");

        nivel=3;
    }

    public void atras(View v){
        facil.setVisibility(View.VISIBLE);
        medio.setVisibility(View.VISIBLE);
        dificil.setVisibility(View.VISIBLE);
        atras.setVisibility(View.GONE);
        empezar.setVisibility(View.GONE);
        introduccion.setText("Logra poner en práctica todos tus conocimientos acerca del ciclo de vida de software general que se utilizan para el desarrollo de proyectos en la actualidad.");
    }

    public void empezar(View v){
        i = new Intent(getApplicationContext(), PantallaJugarActivity.class);
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