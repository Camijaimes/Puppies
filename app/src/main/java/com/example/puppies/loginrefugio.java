package com.example.puppies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class loginrefugio extends AppCompatActivity {
    EditText etusuario, etcontra;
    Button btingresar;
    TextView etpersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginrefugio);

        etusuario = findViewById(R.id.etusuario);
        etcontra = findViewById(R.id.etcontra);
        btingresar = findViewById(R.id.btingresar);
        etpersona = findViewById(R.id.etpersona);

        etpersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), loginpersona.class);
                startActivityForResult(intent, 0);
            }
        });

        btingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar("https://proyectopuppies.000webhostapp.com/loginrefugio.php");
            }
        });
    }

    private void validar(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), iniciopersona.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(loginrefugio.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(loginrefugio.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("nom_refugio",etusuario.getText().toString());
                parametros.put("pass_refugio",etcontra.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
