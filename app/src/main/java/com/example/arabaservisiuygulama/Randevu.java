package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Randevu extends AppCompatActivity {

    EditText textInputEditTextMarka, textInputEditTextModel, textInputEditTextSeri, textInputEditTextPlaka, TextInputEditTextKilometre;

    Button buttonRandevuOlustur;

    Spinner spinnerYakit;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu);

        buttonRandevuOlustur = (Button)findViewById(R.id.randevuOlustur);

        textInputEditTextMarka = findViewById(R.id.marka);
        textInputEditTextModel = findViewById(R.id.model);
        textInputEditTextSeri = findViewById(R.id.seri);
        textInputEditTextPlaka = findViewById(R.id.plaka);
        TextInputEditTextKilometre = findViewById(R.id.kilometre);

        spinnerYakit = findViewById(R.id.yakit);


        buttonRandevuOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marka, model, seri, plaka, kilometre, yakit;
                marka = String.valueOf(textInputEditTextMarka.getText());
                model = String.valueOf(textInputEditTextModel.getText());
                seri = String.valueOf(textInputEditTextSeri.getText());
                plaka = String.valueOf(textInputEditTextPlaka.getText());
                kilometre = String.valueOf(TextInputEditTextKilometre.getText());
                yakit = spinnerYakit.getSelectedItem().toString();


                if (!marka.equals("") && !model.equals("") && !seri.equals("") && !plaka.equals("") && !kilometre.equals("") && !yakit.equals("")){
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[6];
                            field[0] = "marka";
                            field[1] = "model";
                            field[2] = "seri";
                            field[3] = "plaka";
                            field[4] = "kilometre";
                            field[5] = "yakit";


                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = marka;
                            data[1] = model;
                            data[2] = seri;
                            data[3] = plaka;
                            data[4] = kilometre;
                            data[5] = yakit;

                            PutData putData = new PutData("http://192.168.0.29/LoginRegister/CarInformation.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Arac Ekleme islemi Basarili!"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
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
                    Toast.makeText(getApplicationContext(), "Gerekli alanlar doldurulmalı", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}