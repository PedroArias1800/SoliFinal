package com.example.solifinal.Services;

import com.example.solifinal.Entidades.CVID_Estudiante;
import com.example.solifinal.Entidades.CVID_Tabla;
import com.example.solifinal.Entidades.Juego;
import com.example.solifinal.Entidades.CVID_Preguntas;
import com.example.solifinal.Entidades.Preguntas;
import com.example.solifinal.Entidades.Respuestas;
import com.example.solifinal.Requests.PartidaRequest;
import com.example.solifinal.Responses.Facultad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php?ep=login")
    Call<CVID_Estudiante> getEstudianteLogin(@Query("u") String u, @Query("p") String p);

    @GET("api.php?ep=juegos")
    Call<List<Juego>> getAllJuegos();

    @GET("api.php?ep=facultades")
    Call<List<Facultad>> getAllFacultades();

    @POST("api.php?ep=estudiantesSave")
    Call<Integer> postRegistrarEstudiante(@Body CVID_Estudiante estudiante);

    @POST("api.php?ep=partidaSave")
    Call<Integer> postRegistrarPartida(@Body PartidaRequest partida);

    /*@GET("api.php?ep=preguntas")
    Call<List<CVID_Preguntas>> getPreguntas(@Query("j") int juego);*/

    @GET("api.php?ep=preguntas")
    Call<List<Preguntas>> getPreguntas(@Query("nid") int nid);

    @GET("api.php?ep=posiciones")
    Call<List<CVID_Tabla>> getAllTable(@Query("t") int n);

    @POST("api.php?ep=preguntaSave")
    Call<Integer> postRegistrarPregunta(@Body Preguntas preguntas);

    @GET("api.php?ep=preguntasID")
    Call<List<Respuestas>> getPreguntaByID(@Query("pid") int _pregId);

    @POST("api.php?ep=preguntaEdit")
    Call<Integer> postEditarPregunta(@Body Preguntas preguntas);
}
