package com.example.solifinal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.solifinal.Dialogs.Retroalimentacion;
import com.example.solifinal.Entidades.Preguntas;
import com.example.solifinal.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PantallaJugar extends AppCompatActivity {

    Intent i;

    /*
    Intent ip = getIntent();
    private int puntos = ip.getIntExtra("Puntaje",0);
     */
    private int puntos = 0, nivel;

    private final int _juegoId = 3;

    private TextView m,n,p,t; //Modulo, Nivel, Pregunta, Tiempo
    private Button r1,r2,r3,r4,s; //Respuestas 1-4, siguiente

    private CountDownTimer cdt;
    private long t_res = 20000; //Tiempo restante en milisegundos
    private boolean run; //Verificando que el tiempo este corriendo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallajugar);

        iniControles();
        ObtenerPreguntas();
        Timer();
        i = getIntent();
        nivel = i.getIntExtra("nivel", 1);
    }

    private void iniControles(){
        m = (TextView)findViewById(R.id.mod);
        n = (TextView)findViewById(R.id.niv);
        p = (TextView)findViewById(R.id.pre);
        t = (TextView)findViewById(R.id.time);

        r1 = (Button)findViewById(R.id.res1);
        r2 = (Button)findViewById(R.id.res2);
        r3 = (Button)findViewById(R.id.res3);
        r4 = (Button)findViewById(R.id.res4);
        s = (Button)findViewById(R.id.sig);
    }

    private void ObtenerPreguntas() {
        Call<List<Preguntas>> response = ApiService.getApiService().getPreguntas(_juegoId);
        response.enqueue(new Callback<List<Preguntas>>() {
            @Override
            public void onResponse(Call<List<Preguntas>> call, Response<List<Preguntas>> response) {
                if (response.isSuccessful()){
                    _preguntas = response.body();
                    if (_preguntas.size() > 0){

                        nivel.setText(_preguntas.get(0).getNivel());
                        _preguntaActual = _preguntas.get(0);
                        RenderPrimeraPregunta();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Preguntas>> call, Throwable t) {
                int x = 1;
            }
        });
    }

    public void DeactBotones(){ //Desactiva las respuestas y activa el boton para avanzar
        s.setEnabled(true);
        r1.setEnabled(false);
        r2.setEnabled(false);
        r3.setEnabled(false);
        r4.setEnabled(false);
    }

    private void Timer(){ //Controla si el tiempo continua o se detiene
        if(run){
            Stop();
        } else {
            Start();
        }
    }
    private void Start(){ //Inicia el tiempo
        cdt = new CountDownTimer(t_res, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                t_res = millisUntilFinished;
                ActTimer();
            }

            @Override
            public void onFinish(){}
        }.start();
        run = true;
    }
    private void Stop(){ //Detiene el tiempo
        cdt.cancel();
        run = false;
    }
    private void ActTimer(){ //Muestra el tiempo actual en pantalla
        int sec = (int) t_res/1000;
        String tiempo;
        if(sec<10){
            tiempo = "0" + sec;
        } else {
            tiempo = Integer.toString(sec);
        }
        tiempo+="s";

        if(sec>10){
            t.setTextColor(Color.parseColor("#00FF00"));
        } else if(sec>0){
            t.setTextColor(Color.parseColor("#FFFF00"));
        } else{
            t.setTextColor(Color.parseColor("#FF0000"));
            DeactBotones(); Respuesta("Time Up");
        }

        t.setText(tiempo);
    }

    private void Respuesta(String op){ //Verifica si la respuesta es correcta o no
        DeactBotones(); Stop();
        String correcta = "Respuesta 1"; //Buscar la respuesta correcta de la BD
        if(op.equals(correcta)){
            puntos+=1;
            Toast.makeText(this,"Respuesta Correcta",Toast.LENGTH_LONG).show();
        } else{
            if(op.equals("Time Up")){
                Toast.makeText(this,"Se acabó el tiempo.",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(this,"Respuesta Incorrecta",Toast.LENGTH_LONG).show();
            }
            Retroalimentacion retro = new Retroalimentacion();
            Bundle args = new Bundle();
            args.putString("ResCorrecta",correcta);
            retro.setArguments(args);
            retro.show(getSupportFragmentManager(), "retal");
        }
    }

    //En via la respuesta elegida al metodo Respuesta
    public void Res1(View view){
        String op = r1.getText().toString();
        Respuesta(op);
    } public void Res2(View view){
        String op = r2.getText().toString();
        Respuesta(op);
    } public void Res3(View view){
        String op = r3.getText().toString();
        Respuesta(op);
    } public void Res4(View view){
        String op = r4.getText().toString();
        Respuesta(op);
    }

    //Avanza a la siguiente pregunta
    public void SigPreg(View view){
        Intent i = new Intent(this, PantallaJugar.class);
        i.putExtra("Puntaje", puntos);
        startActivity(i);
    }

}