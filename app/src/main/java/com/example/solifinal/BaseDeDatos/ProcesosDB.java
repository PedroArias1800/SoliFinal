package com.example.solifinal.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.solifinal.Entidades.CVID_Puntaje;
import com.example.solifinal.Entidades.CVID_Usuario;

import java.util.ArrayList;
import java.util.List;

public class ProcesosDB{

    HacerProcesos hacerProcesos;

    public ProcesosDB(Context context) {
        hacerProcesos = new HacerProcesos(context, "juegos", null, 1);
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

                db.insert("session",null,values);
                db.close();
                return true;
            }
        }
        catch (Exception e){}
        return false;
    }

}
