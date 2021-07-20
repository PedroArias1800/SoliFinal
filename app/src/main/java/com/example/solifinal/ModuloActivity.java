package com.example.solifinal;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModuloActivity extends AppCompatActivity {

    private TextView ttl;
    private EditText nm_mod;
    private ImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo);

        InicializarControles();
    }

    private void InicializarControles(){
        ttl = (TextView)findViewById(R.id.ttlMod);
        nm_mod = (EditText) findViewById(R.id.nomModulo);
        img = (ImageView) findViewById(R.id.imgModulo);
    }
}
