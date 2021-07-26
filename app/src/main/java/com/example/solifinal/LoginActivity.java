package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.solifinal.BaseDeDatos.ProcesosDB;
import com.example.solifinal.Entidades.CVID_Estudiante;
import com.example.solifinal.Entidades.CVID_Usuario;
import com.example.solifinal.Services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText u, p;
    ProcesosDB _db;
    MediaPlayer click, music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _db = new ProcesosDB(getApplicationContext());

        u = (EditText)findViewById(R.id.txtUser);
        p = (EditText)findViewById(R.id.txtPasss);

        click = MediaPlayer.create(this, R.raw.click);

        music = MediaPlayer.create(this, R.raw.menumusic);
        music.start();

        ValidarSession();

    }

    @Override
    protected void onStart(){
        super.onStart();
        music.start();
    }

    private void ValidarSession() {
        CVID_Usuario user = _db.ObtenerUsuarioSession();
        if (user != null){
            startActivity(new Intent(getApplicationContext(),MenuLoginActivity.class));
        }
    }

    public void IniciarSesion(View v){
        try {
            click.start();
            String user = u.getText().toString();
            String pass = p.getText().toString();

            Call<CVID_Estudiante> response = ApiService.getApiService().getEstudianteLogin(user,pass);
            response.enqueue(new Callback<CVID_Estudiante>() {
                @Override
                public void onResponse(Call<CVID_Estudiante> call, Response<CVID_Estudiante> response) {
                    if (response.isSuccessful()){
                        CVID_Estudiante estudiante = response.body();
                        if (estudiante != null){

                            CVID_Usuario user =
                                    new CVID_Usuario(
                                Integer.parseInt(estudiante.getId()),
                                estudiante.getEmail(),
                                "",
                                estudiante.getNombre_completo()
                            );

                            _db.GuardarSessionUsuario(user);

                            Toast.makeText(getApplicationContext(),"Login Exitoso",Toast.LENGTH_LONG).show();

                            estudiante.setTipo(3);

                            Intent i = new Intent(getApplicationContext(),MensajeLoginActivity.class);

                            i.putExtra("Nombre", estudiante.getNombre_completo());
                            i.putExtra("Tipaje", estudiante.getTipo());

                            startActivity(i);

                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Error Al Iniciar Sesión",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<CVID_Estudiante> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Error Al Iniciar Sesión",Toast.LENGTH_LONG).show();
                    int x = 1;
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error Al Iniciar Sesión",Toast.LENGTH_LONG).show();
            int x = 1;
        }
    }

    public void Registrarse(View v){
        click.start();
        Intent i = new Intent(getApplicationContext(), RegistrarseActivity.class);
        i.putExtra("num", 1);
        startActivity(i);
    }

    public void RecuperarContraseña(View view) {
        click.start();
        startActivity(new Intent(getApplicationContext(), RecuperarContrasenaActivity.class));
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