package com.example.solifinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

        _db = new ProcesosDB(getApplicationContext());

        u = (EditText)findViewById(R.id.txtUser);
        p = (EditText)findViewById(R.id.txtPasss);

        _db.CerrarSesion();
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

                            Toast.makeText(getApplicationContext(),"Login Exitoso",Toast.LENGTH_LONG).show();

                                estudiante.setTipo(3);


                            Intent i = new Intent(getApplicationContext(),MensajeLoginActivity.class);

                            i.putExtra("Nombre", estudiante.getNombre_completo());
                            i.putExtra("Tipaje", estudiante.getTipo());
                            _db.AbrirSesion(Integer.parseInt(estudiante.getId()), estudiante.getTipo());
                            startActivity(i);

                        }
                    }else {
                        int x = 1;
                    }
                }

                @Override
                public void onFailure(Call<CVID_Estudiante> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Error Al Iniciar Sesión",Toast.LENGTH_LONG).show();
                    int x = 1;
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error Al Iniciar Sesión",Toast.LENGTH_LONG).show();
            int x = 1;
        }
    }

    public void Registrarse(View v){
        startActivity(new Intent(getApplicationContext(), RegistrarseActivity.class));
    }

    public void RecuperarContraseña(View view) {
        startActivity(new Intent(getApplicationContext(), RecuperarContrasenaActivity.class));
    }

    public void Utp(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://utp.ac.pa/"));
        startActivity(i);
    }

    public void UtpFisc(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fisc.utp.ac.pa/"));
        startActivity(i);
    }
}