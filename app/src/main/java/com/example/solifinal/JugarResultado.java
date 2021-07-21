package com.example.solifinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JugarResultado extends AppCompatActivity {

    private TextView m,n,p; //Modulo, nivel, puntaje
    private Button vol; //volver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar_resultado);

        IniControles();
    }

    private void IniControles(){
        m = (TextView)findViewById(R.id.modnum);
        n = (TextView)findViewById(R.id.nivnum);
        p = (TextView)findViewById(R.id.pts);
    }

}