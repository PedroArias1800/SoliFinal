package com.example.solifinal.ListView;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.solifinal.Entidades.CVID_Tabla;
import com.example.solifinal.Entidades.Partida;
import com.example.solifinal.R;

import java.util.ArrayList;
import java.util.List;

public class TablaListViewAdapter extends ArrayAdapter<CVID_Tabla> {
    List<CVID_Tabla> tabla = new ArrayList<>();

    //static int y=0;
    //int x=0;

    public TablaListViewAdapter(Context context, List<CVID_Tabla> datos){
        super(context, R.layout.listview_pranking,datos);
        tabla = datos;
        //y=0;
    }

    public View getView(int position, View v, ViewGroup vg){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_pranking, null);
/*
        y=y+1;

        TextView pos = (TextView) item.findViewById(R.id.txtPosicion);
        pos.setText("Posición: " + x);*/

        TextView juego = (TextView)item.findViewById(R.id.lslblJuego);
        juego.setText("Juego: " + tabla.get(position).getJuego());

        TextView jugador = (TextView)item.findViewById(R.id.lslblJugador);
        jugador.setText("Jugador: "+tabla.get(position).getJugador());

        TextView nivel = (TextView)item.findViewById(R.id.lslblNivel);
        nivel.setText("Nivel: "+tabla.get(position).getNivel());

        TextView puntaje = (TextView)item.findViewById(R.id.lslblPuntaje);
        puntaje.setText("Puntaje: "+tabla.get(position).getPuntaje());

        TextView fecha = (TextView)item.findViewById(R.id.lslblFecha);
        fecha.setText("Partida Jugada: "+tabla.get(position).getFecha());

        TextView partida = (TextView)item.findViewById(R.id.lslblPartida);
        partida.setText("# Partida: "+tabla.get(position).getPartida());

        return item;
    }
}
