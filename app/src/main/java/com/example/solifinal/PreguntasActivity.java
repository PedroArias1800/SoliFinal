package com.example.solifinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.solifinal.Entidades.Preguntas;
import com.example.solifinal.Entidades.Respuestas;
import com.example.solifinal.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreguntasActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txt_p, txt_r, txt_ri1, txt_ri2, txt_ri3;
    Spinner spn_tp, spn_nv;
    LinearLayout ll_mr;
    LinearLayout ll_vyf;
    RadioGroup rg_vyf;
    RadioButton rb_v, rb_f;
    Button btn_save, btn_edit;

    Intent _i;
    List<Respuestas> _respuestas = new ArrayList<>();

    int _idtipo, _pregId, _tipoId, _rgt_o_edt;
    String _preg, _nvl;

    String[] tipo_p = {"Mejor respuesta", "Verdadero y Falso"};
    String[] nivel_p = {"Fácil", "Medio", "Difícil"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        InicializarControles();
        SetSpinners();

        _i = getIntent();
        int edit = _i.getIntExtra("edit", 0);
        if(edit == 1)
            InicializarPregunta();
    }

    private void InicializarPregunta(){
        try{
            _i = getIntent();
            _pregId = Integer.parseInt(_i.getStringExtra("preguntaId"));
            _preg = _i.getStringExtra("pregunta");
            _tipoId = Integer.parseInt(_i.getStringExtra("tipoId"));
            _nvl = _i.getStringExtra("nivel");

            Call<List<Respuestas>> response = ApiService.getApiService().getPreguntaByID(_pregId);
            response.enqueue(new Callback<List<Respuestas>>() {
                @Override
                public void onResponse(Call<List<Respuestas>> call, Response<List<Respuestas>> response) {
                    if (response.isSuccessful()){
                        _respuestas = response.body();

                        txt_p.setText(_preg);

                        if(_nvl.equals("Novato"))
                            spn_nv.setSelection(0);
                        else if(_nvl.equals("Semi Hardcore"))
                            spn_nv.setSelection(1);
                        else if(_nvl.equals("Super Harcore Extreme"))
                            spn_nv.setSelection(2);
                        else
                            spn_nv.setSelection(0);

                        if (_tipoId == 4) {
                            ll_mr.setVisibility(View.VISIBLE);

                            txt_r.setText(_respuestas.get(0).getRespuesta());
                            txt_ri1.setText(_respuestas.get(1).getRespuesta());
                            txt_ri2.setText(_respuestas.get(2).getRespuesta());
                            txt_ri3.setText(_respuestas.get(3).getRespuesta());
                        }
                        else if (_tipoId == 3){
                            spn_tp.setSelection(1);
                            ll_vyf.setVisibility(View.VISIBLE);

                            if( (_respuestas.get(0).getRespuesta()).equals("Verdadero"))
                                rb_v.setChecked(true);
                            else if(_respuestas.get(0).getRespuesta().equals("Falso"))
                                rb_f.setChecked(true);
                        }

                        btn_save.setVisibility(View.GONE);
                        btn_edit.setVisibility(View.VISIBLE);
                    }else{
                        int x = 1;
                    }
                }

                @Override
                public void onFailure(Call<List<Respuestas>> call, Throwable t) {
                    int x = 1;
                }
            });


        }catch (Exception e){}
    }

    private void InicializarControles(){
        txt_p = (EditText) findViewById(R.id.pregunta);
        txt_r = (EditText) findViewById(R.id.respCorecta);
        txt_ri1 = (EditText) findViewById(R.id.posibleResp1);
        txt_ri2 = (EditText) findViewById(R.id.posibleResp2);
        txt_ri3 = (EditText) findViewById(R.id.posibleResp3);

        spn_tp = (Spinner) findViewById(R.id.spnTipoPreg);
        spn_nv = (Spinner) findViewById(R.id.spnNivelPreg);

        ll_mr = (LinearLayout) findViewById(R.id.tipoPregMR);
        ll_vyf = (LinearLayout) findViewById(R.id.tipoPregVyF);

        rg_vyf = (RadioGroup) findViewById(R.id.rgVyF);
        rb_v = (RadioButton) findViewById(R.id.verdadero);
        rb_f = (RadioButton) findViewById(R.id.falso);

        btn_save = (Button) findViewById(R.id.btnGuardar);
        btn_edit = (Button) findViewById(R.id.btnGuardarEdit);
    }

    public void SetSpinners() {
        ArrayAdapter<String> adapterTipo =
                new ArrayAdapter<String>(this, R.layout.spinner_item, tipo_p);
        spn_tp.setAdapter(adapterTipo);
        spn_tp.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapterNivel =
                new ArrayAdapter<String>(this, R.layout.spinner_item, nivel_p);
        spn_nv.setAdapter(adapterNivel);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            String tpp = parent.getItemAtPosition(position).toString();

            if(tpp.equals("Mejor respuesta")){
                ll_vyf.setVisibility(View.GONE);
                ll_mr.setVisibility(View.VISIBLE);

                _idtipo = 4;
            }
            else if(tpp.equals("Verdadero y Falso")){
                ll_mr.setVisibility(View.GONE);
                ll_vyf.setVisibility(View.VISIBLE);

                _idtipo = 3;
            }
        }catch (Exception e){ }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ll_mr.setVisibility(View.GONE);
        ll_vyf.setVisibility(View.GONE);
    }

    // Llama al metodo registrar pregunta e indica que se va a ser un registro
    public void SaveRegistrarPregunta(View view){
        _rgt_o_edt = 0;
        RegistarPregunta();
    }

    // Llama al metodo registrar pregunta e indica que se va a ser una edicion
    public void SaveEditarPregunta(View view){
        _rgt_o_edt = 1;
        RegistarPregunta();
    }

    private void RegistarPregunta(){

        Preguntas preguntas = new Preguntas();

        try {
            preguntas.setPregunta(txt_p.getText().toString());
            preguntas.setTipo_preg_id(_idtipo);

            if((spn_nv.getSelectedItem().toString()).equals("Fácil"))
                preguntas.setNvl(1);
            else if((spn_nv.getSelectedItem().toString()).equals("Medio"))
                preguntas.setNvl(2);
            else if((spn_nv.getSelectedItem().toString()).equals("Difícil"))
                preguntas.setNvl(3);

            if(_idtipo == 4)
                RespuestaMR(preguntas);
            else if(_idtipo == 3)
                RespuestaVyF(preguntas);

        }catch (Exception e){
            int x= 1;
        }
    }

    // Obtiene las respuestas en tipo Mejor respuesta
    private void RespuestaMR(Preguntas preguntas){
        List<Respuestas> respuestas = new ArrayList<>();
        Respuestas rsp_c = new Respuestas();
        Respuestas rsp_i1 = new Respuestas();
        Respuestas rsp_i2 = new Respuestas();
        Respuestas rsp_i3 = new Respuestas();

        try {
            rsp_c.setRespuesta(txt_r.getText().toString());
            respuestas.add(rsp_c);

            rsp_i1.setRespuesta(txt_ri1.getText().toString());
            respuestas.add(rsp_i1);

            rsp_i2.setRespuesta(txt_ri2.getText().toString());
            respuestas.add(rsp_i2);

            rsp_i3.setRespuesta(txt_ri3.getText().toString());
            respuestas.add(rsp_i3);

            preguntas.setRespuestas(respuestas);

            if(_rgt_o_edt == 0)
                InsertarPregunta(preguntas);
            if(_rgt_o_edt == 1)
                EditarPregunta(preguntas);
        }catch (Exception e){}
    }

    // Obtiene las respuestas en tipo Verdadero y falso
    private void RespuestaVyF(Preguntas preguntas){
        List<Respuestas> respuestas = new ArrayList<>();
        Respuestas rsp_c = new Respuestas();
        Respuestas rsp_i = new Respuestas();

        try {
            switch (rg_vyf.getCheckedRadioButtonId()){
                case R.id.verdadero:
                    rsp_c.setRespuesta("Verdadero");
                    respuestas.add(rsp_c);

                    rsp_i.setRespuesta("Falso");
                    respuestas.add(rsp_i);
                    break;
                case R.id.falso:
                    rsp_c.setRespuesta("Falso");
                    respuestas.add(rsp_c);

                    rsp_i.setRespuesta("Verdadero");
                    respuestas.add(rsp_i);
                    break;
            }

            preguntas.setRespuestas(respuestas);

            if(_rgt_o_edt == 0)
                InsertarPregunta(preguntas);
            if(_rgt_o_edt == 1)
                EditarPregunta(preguntas);
        }catch (Exception e){}
    }

    private  void InsertarPregunta(Preguntas preguntas){
        try{
            Call<Integer> response = ApiService.getApiService().postRegistrarPregunta(preguntas);
            response.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()){
                        int x = 1;
                    }else{
                        int x = 1;
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    int x = 1;
                }
            });
        } catch (Exception e){

        }
    }

    private  void EditarPregunta(Preguntas preguntas){
        try{
            List<Respuestas> respuestas = new ArrayList<>();
            Respuestas rsp_c = new Respuestas();
            Respuestas rsp_i1 = new Respuestas();
            Respuestas rsp_i2 = new Respuestas();
            Respuestas rsp_i3 = new Respuestas();

            rsp_c.setRespuesta_id(Integer.parseInt(_respuestas.get(4).getId()));
            respuestas.add(rsp_c);
            rsp_i1.setRespuesta_id(Integer.parseInt(_respuestas.get(5).getId()));
            respuestas.add(rsp_i1);
            rsp_i2.setRespuesta_id(Integer.parseInt(_respuestas.get(6).getId()));
            respuestas.add(rsp_i2);
            rsp_i3.setRespuesta_id(Integer.parseInt(_respuestas.get(7).getId()));
            respuestas.add(rsp_i3);

            preguntas.setRespuestas(respuestas);

            preguntas.setPregunta_id(_pregId);

            Call<Integer> response = ApiService.getApiService().postEditarPregunta(preguntas);
            response.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()){
                        int x = 1;
                    }else{
                        int x = 1;
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    int x = 1;
                }
            });
        } catch (Exception e){

        }
    }

    //Metodo para volver a la pantalla de Mantenimiento de preguntas
    public void VolverAMantPreg(View view){
        startActivity(new Intent(getApplicationContext(), MantDePreguntasActivity.class));
    }


}
