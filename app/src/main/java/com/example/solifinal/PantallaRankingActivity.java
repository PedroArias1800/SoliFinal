package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.solifinal.BaseDeDatos.ProcesosDB;
import com.example.solifinal.ListView.AdapterRankingPuntaje;

public class PantallaRankingActivity extends AppCompatActivity {

    ListView lstranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallaranking);

        IniciarlizarControles();
    }

    public void IniciarlizarControles(){

        lstranking = (ListView)findViewById(R.id.lstRanking);
        ProcesosDB Pdb = new ProcesosDB(getApplicationContext());
        //AdapterRanking adapterRanking = new AdapterRanking(getApplicationContext(), Pdb.ObtenerRanking());//CREAR EL MOTEDO EN DB PROCESS
        //lstranking.setAdapter(adapterRanking);
        AdapterRankingPuntaje adapterRankingPuntaje = new AdapterRankingPuntaje(getApplicationContext(), Pdb.ObtenerRanking2());
        lstranking.setAdapter(adapterRankingPuntaje);

        lstranking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            //    String opcionSeleccionada =
                       // ((CVID_Usuario) a.getItemAtPosition(position)).getFirstname();

            //    Toast.makeText(P_Ranking.this,  (position+1)+" Posición del ranking: " + opcionSeleccionada, Toast.LENGTH_LONG).show();
            }
        });
    }

}