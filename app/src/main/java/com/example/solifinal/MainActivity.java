package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.solifinal.BaseDeDatos.ProcesosDB;
import com.example.solifinal.Entidades.CVID_Estudiante;
import com.example.solifinal.Entidades.CVID_Usuario;
import com.example.solifinal.Services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText u, p;
    ProcesosDB _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u = (EditText)findViewById(R.id.txtUser);
        p = (EditText)findViewById(R.id.txtPass);
    }

    public void IniciarSesion(View v){
        try {
            String user = u.getText().toString();
            String pass = p.getText().toString();

            Call<CVID_Estudiante> response = ApiService.getApiService().getEstudianteLogin(user,pass);
            response.enqueue(new Callback<CVID_Estudiante>() {
                @Override
                public void onResponse(Call<CVID_Estudiante> call, Response<CVID_Estudiante> response) {
                    if (response.isSuccessful()){
                        CVID_Estudiante estudiante = response.body();
                        if (estudiante != null){

                            CVID_Usuario user =
                                    new CVID_Usuario(
                                            Integer.parseInt(estudiante.getId()),
                                            estudiante.getEmail(),
                                            "",
                                            estudiante.getNombre_completo()
                                    );

                            _db.GuardarSessionUsuario(user);

                            Toast.makeText(getApplicationContext(),"Se loguea coool desde el api",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),MensajeLoginActivity.class);
                            i.putExtra("Nombre", estudiante.getNombre_completo());
                            i.putExtra("Tipo", estudiante.getTipo());
                        }
                    }else {
                        int x = 1;
                    }
                }

                @Override
                public void onFailure(Call<CVID_Estudiante> call, Throwable t) {
                    int x = 1;
                }
            });
        }catch (Exception e){
            int x = 1;
        }
    }

    public void Registrarse(View v){
        startActivity(new Intent(getApplicationContext(), RegistrarseActivity.class));
    }

    public void RecuperarContraseña(View view) {
        startActivity(new Intent(getApplicationContext(), RecuperarContrasenaActivity.class));
    }
}