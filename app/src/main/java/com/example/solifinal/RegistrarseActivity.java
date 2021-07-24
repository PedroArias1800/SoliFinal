package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.solifinal.Entidades.CVID_Estudiante;
import com.example.solifinal.Responses.Facultad;
import com.example.solifinal.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarseActivity extends AppCompatActivity {

    List<Facultad> _facultades;
    Spinner spnFacultades;
    EditText nombre,cedula,edad,correo,password;
    MediaPlayer click, music;
    Intent i;
    int x;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        click = MediaPlayer.create(this, R.raw.click);

        music = MediaPlayer.create(this, R.raw.menumusic);
        music.start();

        i = getIntent();
        x= i.getIntExtra("num",0);

        InicializarControles();
        LoadSpinner();
    }

    @Override
    protected void onStart(){
        super.onStart();
        music.start();
    }

    private void LoadSpinner() {
        Call<List<Facultad>> response = ApiService.getApiService().getAllFacultades();
        response.enqueue(new Callback<List<Facultad>>() {
            @Override
            public void onResponse(Call<List<Facultad>> call, Response<List<Facultad>> response) {
                if (response.isSuccessful()){
                    _facultades = response.body();
                    List<String> facultadesListString = new ArrayList<String>();
                    for(Facultad facultad : _facultades){
                        facultadesListString.add(facultad.getFacultad());
                    }

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,facultadesListString);

                    spnFacultades.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Facultad>> call, Throwable t) {

            }
        });
    }

    private void InicializarControles() {
        spnFacultades = (Spinner)findViewById(R.id.spnFacultades);

        nombre = (EditText)findViewById(R.id.TextInputNombre);
        cedula = (EditText)findViewById(R.id.TextInputCedula);
        edad = (EditText)findViewById(R.id.txtEdad);
        correo = (EditText)findViewById(R.id.TextInputEmail);
        password = (EditText)findViewById(R.id.TextInputpassword);
        rg = (RadioGroup)findViewById(R.id.rgbTipoUsuarioReg);


        if(x==1){
            rg.setVisibility(View.GONE);
        }
    }

    public void RegistrarEstudiante(View v){
        try {
            CVID_Estudiante estudiante = new CVID_Estudiante();
            estudiante.setNombre_completo(nombre.getText().toString());
            estudiante.setCedula(cedula.getText().toString());
            estudiante.setEdad(edad.getText().toString());
            estudiante.setEmail(correo.getText().toString());
            estudiante.setPassword(password.getText().toString());

            String selectedFac = spnFacultades.getSelectedItem().toString();
            String facultadId = "";
            for(Facultad facultad : _facultades){
                if (facultad.getFacultad().equals(selectedFac)){
                    facultadId = facultad.getId();
                }
            }
            estudiante.setFacultad(facultadId);

            Call<Integer> response = ApiService.getApiService().postRegistrarEstudiante(estudiante);
            response.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()){
                        int x = 1;
                    }else{
                        int x = 1;
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    int x = 1;
                }
            });

        }catch (Exception e){
            int x= 1;
        }
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