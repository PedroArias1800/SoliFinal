package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/*
    public void VerRanking(View v){
        Intent i = new Intent(getApplicationContext(), P_Ranking.class);
        startActivity(i);
    }*/

    public void IniciarSesion(View v){


    }

    public void Registrarse(View v){
        startActivity(new Intent(getApplicationContext(), RegistrarseActivity.class));
    }

    public void RecuperarContraseña(View view) {
        startActivity(new Intent(getApplicationContext(), RecuperarContrasenaActivity.class));
    }
}