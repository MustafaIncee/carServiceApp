package com.example.arabaservisiuygulama;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class OnarimHizmetleri extends AppCompatActivity {


    Button buttonHizmetAl;

    Spinner spinnerHizmet;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onarim_hizmetleri);


        buttonHizmetAl = (Button) findViewById(R.id.buttonHizmetAl);

        spinnerHizmet = findViewById(R.id.spinnerHizmet);



        buttonHizmetAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hizmet;

                hizmet = spinnerHizmet.getSelectedItem().toString();


                if (!hizmet.equals("")) {
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[1];
                            field[0] = "hizmetTuru";


                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = hizmet;


                            PutData putData = new PutData("http://192.168.1.104/LoginRegister/hizmetler.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Servis Kaydi Basarili!")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Hizmetler.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Gerekli Alanlar Doldurulmalı", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}