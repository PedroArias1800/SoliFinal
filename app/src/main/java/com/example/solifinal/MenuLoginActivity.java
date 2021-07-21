package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.solifinal.BaseDeDatos.ProcesosDB;

import org.w3c.dom.Text;

public class MenuLoginActivity extends AppCompatActivity {

    Button jugar, ranking, acercade, info, preg, usuario, salir, cancelar;
    TextView mensajeSalir;
    ImageView imgCancelar, imgSalirwh, imgDesplegar;
    int Tipo;

    Intent i;
    ProcesosDB _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);
        //i.getIntent();
        IniciarlizarControles();
    }

    public void IniciarlizarControles(){
        jugar = (Button)findViewById(R.id.btnJugar);
        ranking = (Button)findViewById(R.id.btnRanking);
        acercade = (Button)findViewById(R.id.btnAcercaDe);
        info = (Button)findViewById(R.id.btnInfografia);
        preg = (Button)findViewById(R.id.btnPregunta);
        usuario = (Button)findViewById(R.id.btnUsuario);
        salir = (Button)findViewById(R.id.btnSalir);
        cancelar = (Button)findViewById(R.id.btnCancelar);
        mensajeSalir = (TextView)findViewById(R.id.txtSalir);
        imgCancelar = (ImageView)findViewById(R.id.imgCancelar);
        imgSalirwh = (ImageView)findViewById(R.id.imgSalirwh);
        imgDesplegar = (ImageView)findViewById(R.id.DesplegarLogOut);

        //Tipo=i.getIntExtra("Tipaje", 3);
        Tipo=3;

        if(Tipo==3){
            jugar.setVisibility(View.VISIBLE);
            ranking.setVisibility(View.VISIBLE);
            acercade.setVisibility(View.VISIBLE);
            info.setVisibility(View.VISIBLE);
        } else if(Tipo==2){
            jugar.setVisibility(View.VISIBLE);
            ranking.setVisibility(View.VISIBLE);
            preg.setVisibility(View.VISIBLE);
        } else {
            usuario.setVisibility(View.VISIBLE);
        }

    }

    public void LogOut(View v){

        if(Tipo==3){
            jugar.setVisibility(View.GONE);
            ranking.setVisibility(View.GONE);
            acercade.setVisibility(View.GONE);
            info.setVisibility(View.GONE);
        } else if(Tipo==2){
            jugar.setVisibility(View.GONE);
            ranking.setVisibility(View.GONE);
            preg.setVisibility(View.GONE);
        } else {
            usuario.setVisibility(View.GONE);
        }

        imgDesplegar.setVisibility(View.GONE);
        mensajeSalir.setVisibility(View.VISIBLE);
        salir.setVisibility(View.VISIBLE);
        cancelar.setVisibility(View.VISIBLE);
        imgCancelar.setVisibility(View.VISIBLE);
        imgSalirwh.setVisibility(View.VISIBLE);
    }

    public void CerrarSesion(View v){
        _db = new ProcesosDB(getApplicationContext());
        _db.CerrarSesion();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void BotonCancelar(View v){

        if(Tipo==3){
            jugar.setVisibility(View.VISIBLE);
            ranking.setVisibility(View.VISIBLE);
            acercade.setVisibility(View.VISIBLE);
            info.setVisibility(View.VISIBLE);
        } else if(Tipo==2){
            jugar.setVisibility(View.VISIBLE);
            ranking.setVisibility(View.VISIBLE);
            preg.setVisibility(View.VISIBLE);
        } else {
            usuario.setVisibility(View.VISIBLE);
        }

        imgDesplegar.setVisibility(View.VISIBLE);
        mensajeSalir.setVisibility(View.GONE);
        salir.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        imgCancelar.setVisibility(View.GONE);
        imgSalirwh.setVisibility(View.GONE);
    }

    public void OpcionJugar(View view) {
    }

    public void OpcionRanking(View view) {
    }

    public void OpcionAcercaDe(View view) {
    }

    public void OpcionInfografia(View view) {
    }

    public void OpcionAdPreguntas(View view) {
    }

    public void OpcionAdUsuarios(View view) {
    }

    public void Utp(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://utp.ac.pa/"));
        startActivity(i);
    }

    public void UtpFisc(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fisc.utp.ac.pa/"));
        startActivity(i);
    }
}

