package com.example.solifinal.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.solifinal.Entidades.CVID_Puntaje;
import com.example.solifinal.Entidades.CVID_Usuario;
import com.example.solifinal.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRankingPuntaje extends ArrayAdapter<CVID_Puntaje> {

    private List<CVID_Puntaje> ranking = new ArrayList<>();

    public AdapterRankingPuntaje(Context context, List<CVID_Puntaje> datos) {
        super(context, R.layout.listview_pranking, datos);

        ranking = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_pranking, null);

        TextView lblPuntaje = (TextView) item.findViewById(R.id.lblPuntaje);
        lblPuntaje.setText(Integer.toString(ranking.get(position).getPuntaje()));

        TextView lblPosicion = (TextView) item.findViewById(R.id.lblPosicion);
        lblPosicion.setText(Integer.toString(ranking.get(position).getPuntaje()));

        return (item);
    }
}
