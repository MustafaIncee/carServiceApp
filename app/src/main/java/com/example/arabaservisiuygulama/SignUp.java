package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    TextInputEditText textInputEditTextKullaniciAd, textInputEditTextKullaniciSoyad, textInputEditTextSifre, textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextKullaniciAd = findViewById(R.id.kullaniciAd);
        textInputEditTextKullaniciSoyad = findViewById(R.id.kullaniciSoyad);
        textInputEditTextSifre = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kullaniciAd, kullaniciSoyad, sifre, email;
                kullaniciAd = String.valueOf(textInputEditTextKullaniciAd.getText());
                kullaniciSoyad = String.valueOf(textInputEditTextKullaniciSoyad.getText());
                sifre = String.valueOf(textInputEditTextSifre.getText());
                email = String.valueOf(textInputEditTextEmail.getText());

                if (!kullaniciAd.equals("") && !kullaniciSoyad.equals("") && !sifre.equals("") && !email.equals("")){
                    progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[4];
                        field[0] = "kullaniciAd";
                        field[1] = "kullaniciSoyad";
                        field[2] = "sifre";
                        field[3] = "email";

                        //Creating array for data
                        String[] data = new String[4];
                        data[0] = kullaniciAd;
                        data[1] = kullaniciSoyad;
                        data[2] = sifre;
                        data[3] = email;

                        PutData putData = new PutData("http://192.168.0.26/LoginRegister/signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Kayit Olma islemi Basarili!"))
                                {
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
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