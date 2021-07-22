package com.example.solifinal;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
        Intent i = getIntent();
    }

    public void btnInfo (View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("Información")
                .setMessage("Info\nInfo\nInfo\nInfo\nInfo")
                .setPositiveButton(android.R.string.ok,null)
                .show();
    }

    public void btnEquipo (View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("Equipo de Desarrollo")
                .setMessage("Info\nInfo\nInfo\nInfo\nInfo")
                .setPositiveButton(android.R.string.ok,null)
                .show();
    }

    public void btnYT(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"));
        startActivity(i);
    }

    public void btnFB(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
        startActivity(i);
    }

    public void btnIG(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"));
        startActivity(i);
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
