package com.example.solifinal;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PreguntasActivity extends AppCompatActivity {

    EditText txt_e, txt_r, txt_pr1, txt_pr2, txt_pr3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        InicializarControles();
    }

    private void InicializarControles(){
        txt_e = (EditText) findViewById(R.id.enunciado);
        txt_r = (EditText) findViewById(R.id.respCorecta);
        txt_pr1 = (EditText) findViewById(R.id.posibleResp1);
        txt_pr2 = (EditText) findViewById(R.id.posibleResp2);
        txt_pr3 = (EditText) findViewById(R.id.posibleResp3);
    }
}
