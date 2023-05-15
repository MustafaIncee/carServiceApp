package com.example.arabaservisiuygulama;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AracSil extends AppCompatActivity  {

    Spinner spinnerPlaka;

    Button aracSilButton;
    ArrayList<String> plakaList = new ArrayList<>();
    ArrayAdapter<String> plakaAdapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_sil);
        requestQueue = Volley.newRequestQueue(this);
        spinnerPlaka = findViewById(R.id.aracPlakaSpinner);
        aracSilButton=findViewById(R.id.aracSilButton);


        String url = "http://192.168.1.104/LoginRegister/listServiceType.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("arababilgileri");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String plaka = jsonObject.optString("plaka");
                        plakaList.add(plaka);
                        plakaAdapter = new ArrayAdapter<>(AracSil.this,
                                android.R.layout.simple_spinner_item, plakaList);
                        plakaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerPlaka.setAdapter(plakaAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        aracSilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plakaSil;
                plakaSil=spinnerPlaka.getSelectedItem().toString();


                if (!plakaSil.equals("")){
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[1];
                            field[0] = "plaka";


                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = plakaSil;


                            PutData putData = new PutData("http://192.168.1.104/LoginRegister/aracSil.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Arac Silme islemi Basarili!"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Gerekli alanlar doldurulmalÄ±", Toast.LENGTH_SHORT).show();
                }
            }


        });}
}