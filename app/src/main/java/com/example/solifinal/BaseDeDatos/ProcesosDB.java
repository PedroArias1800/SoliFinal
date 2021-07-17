package com.example.solifinal.BaseDeDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.solifinal.Entidades.CVID_Puntaje;
import com.example.solifinal.Entidades.CVID_Usuario;

import java.util.ArrayList;
import java.util.List;

public class ProcesosDB{

    HacerProcesos puntaje; // CREANDO UNA VARIABLE DE TIPO UsuarioDbHelper//

    public ProcesosDB(Context context) { // EL CONTEXTO SIEMPRE CAMBIA POR ACTIVITY POR ESO DEBO DE RECIBIRLO//
        puntaje = new HacerProcesos(context, "SOLI", null, 1); // ULTIMO VALOR ES LA VERSION DE LA BASE DE DATOS EMPLEADA//
        // _dbc = new ComidaDbHelper(context, "Usuarios", null, 1);//OBJETO DE RECETAS
    }

    // METODO PARA OBTENER INFORMACION DE CVID_PUNTAJE//
    public List<CVID_Usuario> ObtenerRanking() {
        try {
            SQLiteDatabase db = puntaje.getReadableDatabase();
            if (db != null) {
                String[] campos = new String[]{"firstName", "lastName", "email"};//1. CREO UN ARRREGLO PARA CONSULTAR LOS CAMPOS EN LA BD//
                List<CVID_Usuario> U = new ArrayList<>();

                Cursor cursor = db.query("cvid_usuario", campos, null, null, "ID_usuario", null, null); //2. CREAR UN CURSOR Y PASO NOMBRE DE LA TABLA Y CAMPOS A CONSULTAR DE ESA TABLA
                if (cursor.moveToFirst()) {// VERIFICA SI EL CURSOR TIENE DATOS PARA MOVERSE Y LO MUEVE A LA PRIMERA POSICION PARA SABER SI TIENE DATOS//
                    do {//PARA VERIFIACR QUE EXISTA ALGO POR LO MENOS EN LA PRIMERA POSICION
                        CVID_Usuario user = new CVID_Usuario(// JALAR LOS DATOS DE CADA FILA
                                cursor.getString(0),
                                cursor.getString(1),// posicion de las columnas a guardar y ME GUIO DE MI ARREGLO DE CAMPOS//
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

    }

    // METODO PARA OBTENER INFORMACION DE CVID_PUNTAJE//
    public List<CVID_Puntaje> ObtenerRanking2() {
        try {
            SQLiteDatabase db = puntaje.getReadableDatabase();
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

}
