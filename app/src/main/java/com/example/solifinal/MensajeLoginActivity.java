package com.example.solifinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.solifinal.Entidades.CVID_Estudiante;

public class MensajeLoginActivity extends AppCompatActivity {

    ImageView imgCargando;
    TextView txtNombre;

    private String Nombre, Tipaje;
    private int Tipo;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_login);
       Intent i = getIntent();
        Nombre = i.getStringExtra("Nombre");
        Tipo = i.getIntExtra("Tipaje",0);
        InicializarControles();

    }

    public void InicializarControles(){

        if(Tipo==3){
            Tipaje="Estudiante";
        } else if(Tipo==2){
            Tipaje="Docente";
        } else {
            Tipaje="Administrador";
        }

        txtNombre = (TextView)findViewById(R.id.txtNombreLogin);
        txtNombre.setText(Tipaje+" "+Nombre);

        imgCargando = (ImageView)findViewById(R.id.imgCargando);
        imgCargando.setBackgroundResource(R.drawable.cargando);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MenuLoginActivity.class));
            }
        }, 4000);
    }
}