package com.example.solifinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MantDePreguntasActivity extends AppCompatActivity{

    EditText nom;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mant_preguntas);
    }

    public void BotonGuardar(View view)
    {
        name = nom.getText().toString();
        Toast.makeText(this,"Se realizo la busqueda"+name,Toast.LENGTH_SHORT).show();
    }

    public void CrearPregunta(View view) {
        Intent i = new Intent(this, PreguntasActivity.class);
        Toast.makeText(this,"Ya estoy en la segunda pantalla",Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void BusquedaEspecificaDePregunta(View view){

    }

}