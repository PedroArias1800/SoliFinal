package com.example.solifinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
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

    Intent i;
    AnimationDrawable animationDrawable;
    MediaPlayer click, music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_login);
        i = getIntent();
        Nombre = i.getStringExtra("Nombre");
        Tipo = i.getIntExtra("Tipaje",2);

        imgCargando = (ImageView)findViewById(R.id.imgCargando);
        imgCargando.setBackgroundResource(R.drawable.cargando);

        animationDrawable = (AnimationDrawable)imgCargando.getBackground();
        animationDrawable.start();

        click = MediaPlayer.create(this, R.raw.click);

        music = MediaPlayer.create(this, R.raw.resum);

        InicializarControles();

    }

    public void onResume(){
        super.onResume();
        music.start();
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
        txtNombre.setText(Tipaje+"\n"+Nombre);

        imgCargando = (ImageView)findViewById(R.id.imgCargando);
        imgCargando.setBackgroundResource(R.drawable.cargando);

    }

    public void Menu(View v){
        click.start();
        startActivity(new Intent(getApplicationContext(), MenuLoginActivity.class));
    }

    public void Utp(View view) {
        click.start();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://utp.ac.pa/"));
        startActivity(i);
    }

    public void UtpFisc(View view) {
        click.start();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fisc.utp.ac.pa/"));
        startActivity(i);
    }

    @Override
    protected void onPause(){
        super.onPause();
        music.pause();
    }
}