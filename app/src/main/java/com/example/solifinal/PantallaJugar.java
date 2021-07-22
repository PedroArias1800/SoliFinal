package com.example.solifinal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.solifinal.BaseDeDatos.ProcesosDB;
import com.example.solifinal.Dialogs.Retroalimentacion;
import com.example.solifinal.Entidades.CVID_Usuario;
import com.example.solifinal.Entidades.Partida;
import com.example.solifinal.Entidades.Preguntas;
import com.example.solifinal.Entidades.Respuestas;
import com.example.solifinal.Services.ApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PantallaJugar extends AppCompatActivity {

    Intent i;
    List<Preguntas> _preguntas;
    Preguntas _preguntaActual;
    LinearLayout lnRender;
    String _jugador = "";
    List<String> _selectedCheckboxs = new ArrayList<>();

    /*
    Intent ip = getIntent();
    private int puntos = ip.getIntExtra("Puntaje",0);
     */
    private int puntos = 0, ni;

    private final int _juegoId = 3;

    private TextView m,n,p,t, niv, tipo; //Modulo, Nivel, Pregunta, Tiempo
    private Button r1,r2,r3,r4,s; //Respuestas 1-4, siguiente

    private CountDownTimer cdt;
    private long t_res = 20000; //Tiempo restante en milisegundos
    private boolean run; //Verificando que el tiempo este corriendo

    ProcesosDB _db;
    int _numPartida = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallajugar);

        iniControles();
        ObtenerPreguntas();
        Timer();
        i = getIntent();
        ni = i.getIntExtra("nivel", 1);

        _numPartida = _db.ObtenerSiguientePartida();

        ObtenerUsuarioSession();
    }

    private void ObtenerUsuarioSession() {
        CVID_Usuario user = _db.ObtenerUsuarioSession();
        _jugador = user.getNombre();
    }

    private void iniControles(){
        m = (TextView)findViewById(R.id.mod);
        n = (TextView)findViewById(R.id.niv);
        p = (TextView)findViewById(R.id.pre);
        t = (TextView)findViewById(R.id.time);
        niv = (TextView)findViewById(R.id.niv);
        tipo = (TextView)findViewById(R.id.TIPO);
        lnRender = (LinearLayout)findViewById(R.id.renderRespuestas);
/*
        r1 = (Button)findViewById(R.id.res1);
        r2 = (Button)findViewById(R.id.res2);
        r3 = (Button)findViewById(R.id.res3);
        r4 = (Button)findViewById(R.id.res4);
        s = (Button)findViewById(R.id.sig);*/
    }

    private void ObtenerPreguntas() {
        Call<List<Preguntas>> response = ApiService.getApiService().getPreguntas(ni);
        response.enqueue(new Callback<List<Preguntas>>() {
            @Override
            public void onResponse(Call<List<Preguntas>> call, Response<List<Preguntas>> response) {
                if (response.isSuccessful()){
                    _preguntas = response.body();
                    if (_preguntas.size() > 0){

                        niv.setText("Nivel: "+_preguntas.get(0).getNivel());
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

    private void RenderPrimeraPregunta(){
        p.setText(_preguntaActual.getPregunta());
        tipo.setText(_preguntaActual.getTipo());

        if (_preguntaActual.getTipo_pregunta_id().equals("2")){
            RenderPreguntaOpcionMultiple(_preguntaActual);
        }else if(_preguntaActual.getTipo_pregunta_id().equals("3")){
            RenderPreguntaVF(_preguntaActual);
        }else if(_preguntaActual.getTipo_pregunta_id().equals("4")){
            RenderPreguntaMejorOpcion(_preguntaActual);
        }
    }

    private void RenderPregunta(Preguntas preguntaAnterior){
        int indiceActual = _preguntas.indexOf(preguntaAnterior);

        if(indiceActual == _preguntas.size() - 1){
            Intent i = new Intent(getApplicationContext(), JugarResultado.class);
            i.putExtra("Partida",_numPartida);
            startActivity(i);
        }else{
            _preguntaActual = _preguntas.get(_preguntas.indexOf(preguntaAnterior)+1);

            p.setText(_preguntaActual.getPregunta());
            tipo.setText(_preguntaActual.getTipo());

            if (_preguntaActual.getTipo_pregunta_id().equals("2")){
                RenderPreguntaOpcionMultiple(_preguntaActual);
            }else if(_preguntaActual.getTipo_pregunta_id().equals("3")){
                RenderPreguntaVF(_preguntaActual);
            }else if(_preguntaActual.getTipo_pregunta_id().equals("4")){
                RenderPreguntaMejorOpcion(_preguntaActual);
            }
        }
    }

    private void RenderPreguntaMejorOpcion(Preguntas pregunta) {

        RadioGroup group = new RadioGroup(getApplicationContext());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        group.setLayoutParams(params);

        for(Respuestas respuesta : pregunta.getRespuestas()){
            RadioButton newCheck = new RadioButton(getApplicationContext());

            newCheck.setLayoutParams(params);
            newCheck.setText(respuesta.getRespuesta());
            newCheck.setId(View.generateViewId());

            group.addView(newCheck);
        }

        lnRender.addView(group);

        Button validar = new Button(getApplicationContext());
        validar.setLayoutParams(params);
        validar.setText("Validar Respuestas");

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int puntaje = 0;
                RadioButton checkResponse = group.findViewById(group.getCheckedRadioButtonId());
                String selectedResponse = checkResponse.getText().toString();

                for (Respuestas res : pregunta.getRespuestas()){
                    if (res.getRespuesta().equals(selectedResponse) && res.getCorrecta().equals("1")){
                        puntaje = Integer.parseInt(res.getPuntaje());
                    }
                }

                lnRender.removeAllViews();
                RenderPregunta(pregunta);

                GuardarRespuesta(pregunta,selectedResponse,puntaje);
            }
        });

        lnRender.addView(validar);
    }

    private void RenderPreguntaVF(Preguntas pregunta) {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button verdadero = new Button(getApplicationContext());
        Button falso = new Button(getApplicationContext());

        verdadero.setLayoutParams(params);
        falso.setLayoutParams(params);

        verdadero.setText("VERDADERO");
        falso.setText("FALSO");

        verdadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int puntaje = 0;
                String respuestas = "";

                if (pregunta.getRespuestas().get(0).getRespuesta().equals("1")){
                    puntaje = Integer.parseInt(pregunta.getRespuestas().get(0).getPuntaje());
                    respuestas = "1";
                    Toast.makeText(getApplicationContext(),"CORRECTOOOOOOO", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"INCORRECTOOOOOOO", Toast.LENGTH_LONG).show();
                    respuestas = "0";
                }
                GuardarRespuesta(pregunta,respuestas,puntaje);

                lnRender.removeAllViews();
                RenderPregunta(pregunta);
            }
        });

        falso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int puntaje = 0;
                String respuestas = "";

                if (pregunta.getRespuestas().get(0).getRespuesta().equals("0")){
                    puntaje = Integer.parseInt(pregunta.getRespuestas().get(0).getPuntaje());
                    respuestas = "0";
                    Toast.makeText(getApplicationContext(),"CORRECTOOOOOOO", Toast.LENGTH_LONG).show();
                }else{
                    respuestas = "1";
                    Toast.makeText(getApplicationContext(),"INCORRECTOOOOOOO", Toast.LENGTH_LONG).show();
                }
                GuardarRespuesta(pregunta,respuestas,puntaje);

                lnRender.removeAllViews();
                RenderPregunta(pregunta);
            }
        });

        lnRender.addView(verdadero);
        lnRender.addView(falso);
    }

    private void RenderPreguntaOpcionMultiple(Preguntas pregunta) {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        for(Respuestas respuesta : pregunta.getRespuestas()){
            CheckBox newCheck = new CheckBox(getApplicationContext());

            newCheck.setLayoutParams(params);
            newCheck.setText(respuesta.getRespuesta());

            newCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (newCheck.isChecked()){
                        _selectedCheckboxs.add(newCheck.getText().toString());
                    }else{
                        _selectedCheckboxs.remove(newCheck.getText().toString());
                    }
                }
            });

            lnRender.addView(newCheck);
        }

        Button validar = new Button(getApplicationContext());
        validar.setLayoutParams(params);
        validar.setText("Validar Respuestas");

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int puntaje = 0;
                String respuestas = "";
                for (String res : _selectedCheckboxs){
                    respuestas = respuestas + "," + res;
                    for (Respuestas resp : pregunta.getRespuestas()){
                        if (resp.getRespuesta().equals(res) && resp.getCorrecta().equals("1")){
                            puntaje = puntaje + Integer.parseInt(resp.getPuntaje());
                        }
                    }
                }

                GuardarRespuesta(pregunta,respuestas,puntaje);

                lnRender.removeAllViews();
                RenderPregunta(pregunta);
            }
        });

        lnRender.addView(validar);
    }

    private void GuardarRespuesta(Preguntas pregunta, String respuestas, int puntaje){
        try {
            Partida respuesta = new Partida();
            respuesta.setPartida(_numPartida);
            respuesta.setJugador(_jugador);
            respuesta.setJuego("SoLi - Software Life");
            respuesta.setNivel(pregunta.getNivel());
            respuesta.setPregunta(pregunta.getPregunta());
            respuesta.setRespuestas(respuestas);
            respuesta.setPuntaje(puntaje);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat tf = new SimpleDateFormat("hh:mm");
            respuesta.setFecha(df.format(new Date()));
            respuesta.setHora(tf.format(new Date()));

            int d = 1;
            _selectedCheckboxs.clear();
            _db.InsentarRespuestaPartida(respuesta,_numPartida);
        }catch (Exception e){
            int x = 1;
        }
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