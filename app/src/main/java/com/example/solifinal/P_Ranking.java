package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.solifinal.BaseDeDatos.ProcesosDB;
import com.example.solifinal.Entidades.CVID_Usuario;
import com.example.solifinal.ListView.AdapterRanking;
import com.example.solifinal.ListView.AdapterRankingPuntaje;

import java.util.ArrayList;
import java.util.List;

public class P_Ranking extends AppCompatActivity {

    ListView lstranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pranking);

        IniciarlizarControles();
    }

    public void IniciarlizarControles(){

        lstranking = (ListView)findViewById(R.id.lstRanking);
        ProcesosDB Pdb = new ProcesosDB(getApplicationContext());
        AdapterRanking adapterRanking = new AdapterRanking(getApplicationContext(), Pdb.ObtenerRanking());//CREAR EL MOTEDO EN DB PROCESS
        lstranking.setAdapter(adapterRanking);
        AdapterRankingPuntaje adapterRankingPuntaje = new AdapterRankingPuntaje(getApplicationContext(), Pdb.ObtenerRanking2());
        lstranking.setAdapter(adapterRankingPuntaje);

        lstranking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String opcionSeleccionada =
                        ((CVID_Usuario) a.getItemAtPosition(position)).getFirstname();

                Toast.makeText(P_Ranking.this,  (position+1)+" Posición del ranking: " + opcionSeleccionada, Toast.LENGTH_LONG).show();
            }
        });
    }

}