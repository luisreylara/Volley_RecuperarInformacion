package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


// https://scratchya.com.ar/videosandroidjava/volley/listar.php
public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
        tv1.setMovementMethod(new ScrollingMovementMethod());
        rq= Volley.newRequestQueue(this);

    }

    public void recuperar (View view){
        //String url ="http://scratchya.com.ar/videosandroidjava/volley/listar.php";
        String url ="http://datamoviles.tk/recuperar/listar.php";
        JsonArrayRequest requerimiento = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int f=0;f<response.length();f++){
                            try {
                                JSONObject objeto = new JSONObject(response.get(f).toString());
                                tv1.append("Codigo: "+objeto.getString("codigo")+"\n");
                                tv1.append("Descripcion: "+objeto.getString("descripcion")+"\n");
                                tv1.append("Precio: "+objeto.getString("precio")+"\n");
                                tv1.append("____________________________\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
        rq.add(requerimiento);
    }
}