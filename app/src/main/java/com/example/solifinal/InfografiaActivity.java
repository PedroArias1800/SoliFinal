package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class InfografiaActivity extends AppCompatActivity {

    MediaPlayer click, music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infografia);

        click = MediaPlayer.create(this, R.raw.click);

        music = MediaPlayer.create(this, R.raw.menumusic);

    }

    public void onResume(){
        super.onResume();
        music.start();
    }

    public void link1(View view) {
        click.start();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=k8byUApEc4c"));
        startActivity(i);
    }

    public void link2(View view) {
        click.start();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=b9U19JNQNYs"));
        startActivity(i);
    }

    public void link3(View view) {
        click.start();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://flanagan.ugr.es/docencia/2005-2006/2/apuntes/ciclovida.pdf"));
        startActivity(i);
    }

    public void link4(View view) {
        click.start();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://intelequia.com/blog/post/2083/ciclo-de-vida-del-software-todo-lo-que-necesitas-saber"));
        startActivity(i);
    }

    public void link5(View view) {
        click.start();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.powerdata.es/el-valor-de-la-gestion-de-datos/ciclo-de-vida-de-un-sistema-de-informacion-fases-y-componentes#:~:text=Fase%20de%20planificaci%C3%B3n.,y%20designar%20roles%20y%20responsabilidades"));
        startActivity(i);
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
}