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
                .setTitle("Información de SOLI")
                .setMessage("El videojuego SOLI es un juego interactivo que tiene como objetivo ilustrar a las personas de diferentes ámbitos (educativo, administrativo, empresas y otras entidades) sobre el aprendizaje de un modelo de ciclo de vida donde se debe tener en cuenta, la importancia de sus etapas y roles necesarios para tener éxito.")
                .setPositiveButton(android.R.string.ok,null)
                .show();
    }

    public void btnEquipo (View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("Equipo de Desarrollo")
                .setMessage("YURY AGRAZAL" +
                        "\nJORDAN ALVARADO" +
                        "\nEZEQUIEL AMAYA" +
                        "\nPEDRO ARIAS" +
                        "\nLUIS CEBALLOS" +
                        "\nDANIEL GÓMEZ" +
                        "\nJESÚS GUEVARA" +
                        "\nRAMSÉS MONTALVO" +
                        "\nAURELIO MORALES")
                .setPositiveButton(android.R.string.ok,null)
                .show();
    }

    public void btnYT(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/UTPPanama"));
        startActivity(i);
    }

    public void btnFB(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/paginautp"));
        startActivity(i);
    }

    public void btnIG(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/utppanama/"));
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
