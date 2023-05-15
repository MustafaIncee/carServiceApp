package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.Map;

    public class BoyamaKaporta extends AppCompatActivity {


    Button buttonHizmetAl;

    Spinner spinnerHizmet;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boyama_kaporta);


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

                            
                            String[] field = new String[1];
                            field[0] = "hizmetTuru";


                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = hizmet;


                            PutData putData = new PutData("http://192.168.0.29/LoginRegister/hizmetler.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    
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
                    Toast.makeText(getApplicationContext(), "Gerekli alanlar doldurulmalı", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
