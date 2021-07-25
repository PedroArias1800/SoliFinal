package com.example.solifinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MantDeUsuariosActivity extends AppCompatActivity{

    RadioGroup tipo_usr;
    ListView usrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mant_usuarios);

        IniControles();
    }

    private void IniControles(){
        tipo_usr = (RadioGroup)findViewById(R.id.rgbTipoUsuario);
        usrs = (ListView) findViewById(R.id.lstEditarUsuario);
    }

    public void CrearUsuario(View view) {
        Intent i = new Intent(this, AdminCrearUsuarioActivity.class);
        i.putExtra("num", 2);
        startActivity(i);
    }

    public void BusquedaEspecifica(View view){
    }

}