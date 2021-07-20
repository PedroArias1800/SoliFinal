package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuLoginActivity extends AppCompatActivity {

    Button jugar, ranking, acercade, info, preg, usuario, salir, cancelar;
    TextView mensajeSalir;
    ImageView imgCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);

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
    }

    public void LogOut(View v){
        mensajeSalir.setVisibility(View.VISIBLE);
        salir.setVisibility(View.VISIBLE);
        cancelar.setVisibility(View.VISIBLE);
        imgCancelar.setVisibility(View.VISIBLE);
    }

    public void CerrarSesion(View v){

    }

    public void BotonCancelar(View v){
        mensajeSalir.setVisibility(View.GONE);
        salir.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        imgCancelar.setVisibility(View.GONE);
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
}