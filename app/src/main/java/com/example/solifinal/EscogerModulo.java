package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class EscogerModulo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_modulo);
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