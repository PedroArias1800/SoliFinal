package com.example.solifinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.solifinal.Entidades.Preguntas;
import com.example.solifinal.Entidades.Respuestas;
import com.example.solifinal.ListView.PreguntasListViewApdater;
import com.example.solifinal.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MantDePreguntasActivity extends AppCompatActivity{

    ListView lstPreguntas;
    RadioGroup rgNiveles;

    List<Preguntas> _preguntas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mant_preguntas);

        InicializarControles();
        EditarPreguntaSeleccionada();
    }

    private void InicializarControles(){
        lstPreguntas = (ListView) findViewById(R.id.lstPreguntas);
        rgNiveles = (RadioGroup) findViewById(R.id.rgNivelesPreg);
    }

    private void EditarPreguntaSeleccionada() {
        lstPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    Intent i = new Intent(getApplicationContext(),PreguntasActivity.class);

                    i.putExtra("edit", 1);
                    i.putExtra("preguntaId", _preguntas.get(position).getId());
                    i.putExtra("pregunta", _preguntas.get(position).getPregunta());
                    i.putExtra("tipoId", _preguntas.get(position).getTipo_pregunta_id());
                    i.putExtra("nivel", _preguntas.get(position).getNivel());

                    /*List<Respuestas> res = new ArrayList<>();
                    res = _preguntas.get(position).getRespuestas();

                    if (_preguntas.get(position).getTipo_pregunta_id().)

                    System.out.println( res.get(0).getRespuesta() + "\n" +
                            res.get(1).getRespuesta());*/

                    startActivity(i);
                }
                catch (Exception e){
                }
            }
        });
    }

    public void ObtenerPreguntas(View view) {
        int nvl = 0;
        try {
            switch (rgNiveles.getCheckedRadioButtonId()){
                case R.id.facil:
                    nvl = 1;
                    break;
                case R.id.medio:
                    nvl = 2;
                    break;
                case R.id.dificil:
                    nvl = 3;
                    break;
            }

            if(nvl != 0) {
                Call<List<Preguntas>> response = ApiService.getApiService().getPreguntas(nvl);
                response.enqueue(new Callback<List<Preguntas>>() {
                    @Override
                    public void onResponse(Call<List<Preguntas>> call, Response<List<Preguntas>> response) {
                        if (response.isSuccessful()) {
                            _preguntas = response.body();

                            PreguntasListViewApdater adapter = new PreguntasListViewApdater(getApplicationContext(), _preguntas);
                            lstPreguntas.setAdapter(adapter);

                            lstPreguntas.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Preguntas>> call, Throwable t) {
                        int x = 1;
                    }
                });
            }
            else
                Toast.makeText(this, "Elija el nivel", Toast.LENGTH_LONG).show();
        } catch (Exception e){ }
    }

    public void CrearPregunta(View view) {
        Intent i = new Intent(this, PreguntasActivity.class);
        //Toast.makeText(this,"Ya estoy en la segunda pantalla",Toast.LENGTH_SHORT).show();
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