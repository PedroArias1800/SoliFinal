package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.solifinal.ListView.TablaListViewAdapter;
import com.example.solifinal.Entidades.CVID_Tabla;
import com.example.solifinal.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PantallaRankingActivity extends AppCompatActivity {

    ListView lstTabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallaranking);

        InicializarControles();
        LoadListView();
    }

    private void LoadListView() {
        Call<List<CVID_Tabla>> response = ApiService.getApiService().getAllTable();
        response.enqueue(new Callback<List<CVID_Tabla>>() {
            @Override
            public void onResponse(Call<List<CVID_Tabla>> call, Response<List<CVID_Tabla>> response) {
                if (response.isSuccessful()){
                    List<CVID_Tabla> table = response.body();
                    TablaListViewAdapter adapter = new TablaListViewAdapter(getApplicationContext(),table);
                    lstTabla.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CVID_Tabla>> call, Throwable t) {

            }
        });
    }

    private void InicializarControles() {
        lstTabla = (ListView)findViewById(R.id.lstTabla);
    }
}