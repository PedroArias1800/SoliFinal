package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.solifinal.Entidades.CVID_Estudiante;

public class MensajeLoginActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    ImageView imgCargando;
    TextView txtNombre;

    private String Nombre, Tipaje;
    private int Tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_login);
        Intent i = getIntent();
        Nombre = i.getStringExtra("Nombre");
        Tipo = i.getIntExtra("Tipo",0);
        InicializarControles();

    }

    public void InicializarControles(){

        if(Tipo==1){
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
                startActivity(new Intent(getApplicationContext(), RegistrarseActivity.class));
            }
        }, 4000);
    }
}