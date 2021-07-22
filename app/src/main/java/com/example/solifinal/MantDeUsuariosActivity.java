package com.example.solifinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MantDeUsuariosActivity extends AppCompatActivity{

    EditText nom;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mant_usuarios);
    }

    public void CrearUsuario(View view) {
        Intent i = new Intent(this, AdminCrearUsuarioActivity.class);
        startActivity(i);
    }

    public void BusquedaEspecifica(View view){
    }

}