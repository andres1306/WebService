package com.soffz.empresa_version25;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsuariosActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    EditText JetUsuario, jetnombre, jetcorreo, jetclave;
    CheckBox jetactivo;
    RequestQueue rq;
    JsonRequest jrq;
    String usr,nombre,coreeo,clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        jetcorreo= findViewById(R.id.etcorreo);
        jetclave= findViewById(R.id.etclave);
        JetUsuario= findViewById(R.id.etusuario);
        jetnombre= findViewById(R.id.etnombre);
        jetactivo= findViewById(R.id.cbactivo);
        rq = Volley.newRequestQueue(this);

    }

    public void adicionar_usuario(View viwe){
        coreeo= jetcorreo.getText().toString();
        nombre= jetnombre.getText().toString();
        clave= jetcorreo.getText().toString();
        usr= JetUsuario.getText().toString();
        if (usr.isEmpty() || nombre.isEmpty()|| coreeo.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            jetcorreo.requestFocus();
        }
        else{
            String url = "http://172.16.58.20:80/Empresa6/registrocorreo.php";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            Limpiar_campos();
                            Toast.makeText(getApplicationContext(), "Registro de usuario realizado correctamente!", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Registro de usuario incorrecto!", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("usr",JetUsuario.getText().toString().trim());
                    params.put("nombre", jetnombre.getText().toString().trim());
                    params.put("correo",jetcorreo.getText().toString().trim());
                    params.put("clave",jetclave.getText().toString().trim());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(postRequest);
        }
    }

    public void Limpiar_campos(){
        JetUsuario.setText("");
        jetnombre.setText("");
        jetcorreo.setText("");
        jetclave.setText("");
    }
    public void ConsultarUsuario(View view ){

        usr = JetUsuario.getText().toString();

        if (usr.isEmpty()){
            Toast.makeText(this, "El campu usuario es necesario", Toast.LENGTH_SHORT).show();
            JetUsuario.requestFocus();
        }
        else {
            String url = "http://172.16.58.20:80/Empresa6/consultar.php?usr="+usr;
            jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            rq.add(jrq);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Erros consulrando usuario", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "El usuario se encuentra registrado", Toast.LENGTH_SHORT).show();
    }
}