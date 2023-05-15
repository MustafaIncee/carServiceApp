package com.example.arabaservisiuygulama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class YeniSifre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_sifre);
        String email=getIntent().getExtras().getString("email");
        EditText editTextYeniSifre = findViewById(R.id.pas2);
        EditText editTextKod= findViewById(R.id.otp);
        Button gonder=findViewById(R.id.buttongo);
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.104/LoginRegister/new-password.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if(response.equals("success")){
                                    Toast.makeText(getApplicationContext(),"Yeni şifre ayarlandı",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        error.printStackTrace();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email", email);
                        paramV.put("otp", editTextKod.getText().toString());
                        paramV.put("new-password", editTextYeniSifre.getText().toString());
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}