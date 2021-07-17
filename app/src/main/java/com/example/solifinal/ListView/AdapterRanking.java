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

public class AdapterRanking extends ArrayAdapter<CVID_Usuario>{

    private List<CVID_Usuario> ranking = new ArrayList<>();

    public AdapterRanking(Context context, List<CVID_Usuario> datos){
        super(context, R.layout.listview_pranking, datos);

        ranking = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_pranking, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
        lblNombre.setText(ranking.get(position).getFirstname()+" "+ranking.get(position).getLastname());

        TextView lblCorreo = (TextView)item.findViewById(R.id.lblCorreo);
        lblCorreo.setText(ranking.get(position).getEmail());

        return(item);
    }

}
