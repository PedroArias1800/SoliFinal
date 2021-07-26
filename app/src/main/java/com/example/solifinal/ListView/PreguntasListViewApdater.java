package com.example.solifinal.ListView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.solifinal.Entidades.Preguntas;
import com.example.solifinal.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class PreguntasListViewApdater extends ArrayAdapter<Preguntas> {
    List<Preguntas> preguntas = new ArrayList<>();

    public PreguntasListViewApdater(Context context, List<Preguntas> datos){
        super(context, R.layout.listview_mant_preguntas,datos);
        preguntas = datos;
    }

    public View getView(int position, View v, ViewGroup vg){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_mant_preguntas, null);

        TextView lblPregunta = (TextView)item.findViewById(R.id.lblPregunta);
        lblPregunta.setText(preguntas.get(position).getPregunta());

        TextView lblTipo = (TextView)item.findViewById(R.id.lblTipo);
        lblTipo.setText(preguntas.get(position).getTipo());

        TextView lblNivel = (TextView)item.findViewById(R.id.lblNivel);
        String nivel = preguntas.get(position).getNivel();

        if(nivel.equals("Novato"))
            lblNivel.setText("Fácil");
        else if(nivel.equals("Semi Hardcore"))
            lblNivel.setText("Medio");
        else if(nivel.equals("Super Harcore Extreme"))
            lblNivel.setText("Difícil");
        else
            lblNivel.setText("");

        return item;
    }
}
