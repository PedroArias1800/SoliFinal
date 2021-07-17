package com.example.solifinal.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HacerProcesos extends SQLiteOpenHelper {

    String cvid_puntaje ="CREATE TABLE cvid_puntaje ( ID_usuario TEXT PRIMARY KEY, experienciaAvance INTEGER, intentoFacil INTEGER, intentoMedio INTEGER, intentoDificil INTEGER, total_p_correcta INTEGER, total_p_incorrecta INTEGER)";
    String cvid_usuario ="CREATE TABLE cvid_usuario ( ID_usuario TEXT PRIMARY KEY, firstName TEXT, lastName TEXT, email TEXT)";
    String cvid_sesion = "CREATE TABLE cvid_sesion ( tipo INTEGER, id INTEGER PRIMARY KEY)";

    public HacerProcesos(Context contex, String dbName, SQLiteDatabase.CursorFactory cursor, int dbVersion){
        super(contex,dbName,cursor,dbVersion);
    }

    /*-- METODOS  POR DEFECTO DE LA CLASE SQLiteOpenHelper --*/
    @Override
    public void onCreate(SQLiteDatabase db) { //SE EJECUTA CUANDO NO EXISTE UNA BASE DE DATOS //
        db.execSQL(cvid_puntaje);
        db.execSQL(cvid_usuario);
        db.execSQL(cvid_sesion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int lastDb, int newDb) { //SE EJECUTA CUANDO EXISTE UNA BASE DE DATOS Y LAS VERSIONES SON DIFERENTES//

    }

}
