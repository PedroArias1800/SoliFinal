package com.example.solifinal.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.solifinal.Entidades.CVID_Puntaje;
import com.example.solifinal.Entidades.CVID_Usuario;
import com.example.solifinal.Entidades.Partida;

import java.util.ArrayList;
import java.util.List;

public class ProcesosDB{

    HacerProcesos hacerProcesos;

    public ProcesosDB(Context context) {
        hacerProcesos = new HacerProcesos(context, "juegos", null, 1);
    }

    public long InsentarRespuestaPartida(Partida partida, int numPartida){
        try{
            SQLiteDatabase db = hacerProcesos.getWritableDatabase();
            if (db != null){
                ContentValues values = new ContentValues();
                values.put("partida",numPartida);
                values.put("jugador",partida.getJugador());
                values.put("juego",partida.getJuego());
                values.put("nivel",partida.getNivel());
                values.put("pregunta",partida.getPregunta());
                values.put("respuestas",partida.getRespuestas());
                values.put("puntaje",partida.getPuntaje());
                values.put("fecha",partida.getFecha());
                values.put("hora",partida.getHora());

                return db.insert("partida",null,values);
            }
        }catch (Exception e){
            int x = 1;
        }
        return 0;
    }
/*
    public List<CVID_Usuario> ObtenerRanking() {
        try {
            SQLiteDatabase db = hacerProcesos.getReadableDatabase();
            if (db != null) {
                String[] campos = new String[]{"firstName", "lastName", "email"};
                List<CVID_Usuario> U = new ArrayList<>();

                Cursor cursor = db.query("cvid_usuario", campos, null, null, "ID_usuario", null, null); //2. CREAR UN CURSOR Y PASO NOMBRE DE LA TABLA Y CAMPOS A CONSULTAR DE ESA TABLA
                if (cursor.moveToFirst()) {
                    do {
                        CVID_Usuario user = new CVID_Usuario(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2)
                        );
                        U.add(user);
                    } while (cursor.moveToNext());
                }
                db.close();
                return U;
            }

        } catch (Exception e) {}
        return null;

    }*/

    public List<CVID_Puntaje> ObtenerRanking2() {
        try {
            SQLiteDatabase db = hacerProcesos.getReadableDatabase();
            if (db != null) {
                String[] campos = new String[]{"experienciaAvance"};//1. CREO UN ARRREGLO PARA CONSULTAR LOS CAMPOS EN LA BD//
                List<CVID_Puntaje> P = new ArrayList<>();

                Cursor cursor = db.query("cvid_puntaje", campos, null, null, null, null, null); //2. CREAR UN CURSOR Y PASO NOMBRE DE LA TABLA Y CAMPOS A CONSULTAR DE ESA TABLA
                if (cursor.moveToFirst()) {// VERIFICA SI EL CURSOR TIENE DATOS PARA MOVERSE Y LO MUEVE A LA PRIMERA POSICION PARA SABER SI TIENE DATOS//
                    do {//PARA VERIFIACR QUE EXISTA ALGO POR LO MENOS EN LA PRIMERA POSICION
                        CVID_Puntaje pun = new CVID_Puntaje(// JALAR LOS DATOS DE CADA FILA
                                cursor.getInt(0)
                        );
                        P.add(pun);
                    } while (cursor.moveToNext());
                }
                db.close();
                return P;
            }

        } catch (Exception e) {}
        return null;

    }

    public Boolean GuardarSessionUsuario(CVID_Usuario usuario){
        try{
            SQLiteDatabase db = hacerProcesos.getWritableDatabase();
            if (db != null){
                db.delete("session",null,null);
                ContentValues values = new ContentValues();
                values.put("id",usuario.getId());
                values.put("user",usuario.getUser());
                values.put("nombre",usuario.getNombre());

                db.insert("cvid_sesion",null,values);
                db.close();
                return true;
            }
        }
        catch (Exception e){}
        return false;
    }

    public CVID_Usuario ObtenerUsuarioSession(){
        try{
            SQLiteDatabase db = hacerProcesos.getReadableDatabase();
            if (db != null){
                String[] campos = new String[]{"id","user","nombre"};
                Cursor cursor = db.query("session",campos,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    CVID_Usuario session = new CVID_Usuario(
                            cursor.getInt(0),
                            cursor.getString(1),
                            "",
                            cursor.getString(2)
                    );
                    return session;
                }
            }
        }
        catch (Exception c){
            return  null;}
        return null;
    }

    public int ObtenerSiguientePartida(){
        int partida = 1;
        try{
            SQLiteDatabase db = hacerProcesos.getReadableDatabase();
            if (db != null){
                String[] campo = {"partida"};

                Cursor cursor = db.query("partida",campo,"juego='3'",null,"partida",null,"partida DESC","1");
                cursor.moveToFirst();
                do {
                    partida = cursor.getInt(0) + 1;
                }while(cursor.moveToNext());

                return partida;
            }
        }catch (Exception e){
            int x = 1;
        }
        return partida;
    }

    public void AbrirSesion(int x, int y){
        try {
            SQLiteDatabase db = hacerProcesos.getWritableDatabase();// PRIMER PASO ABRIR LA BASE DE DATOS PARA ESCRITURA//
            if (db!=null) {
                ContentValues values = new ContentValues();
                values.put("id", x);
                values.put("tipo", y);
                db.insert("cvid_sesion", null, values);// NOMBRE DE LA TABLA , NULL, VALORES DE INSERTAR(REGISTROS CONTENT VALUES)//
                db.close();
            }
        } catch (Exception e) {
        }
    }

    public Boolean CerrarSesion() {
        try {
            SQLiteDatabase db = hacerProcesos.getWritableDatabase();// PRIMER PASO ABRIR LA BASE DE DATOS PARA ESCRITURA//
            if (db != null) {
                db.delete("cvid_sesion", "id", null);// NOMBRE DE LA TABLA , NULL, VALORES DE INSERTAR(REGISTROS CONTENT VALUES)//
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
