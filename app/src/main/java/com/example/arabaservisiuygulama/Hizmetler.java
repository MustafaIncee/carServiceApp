package com.example.arabaservisiuygulama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Hizmetler extends AppCompatActivity {

    Button ButtonPeriyodikBakim,ButtonOnarimHizmetleri,ButtonBoyamaKaporta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hizmetler);

        ButtonPeriyodikBakim = findViewById(R.id.buttonPeriyodikBakim);
        ButtonOnarimHizmetleri = findViewById(R.id.buttonOnarimHizmetleri);
        ButtonBoyamaKaporta = findViewById(R.id.buttonBoyamaveKaporta);

        ButtonPeriyodikBakim.setOnClickListener(new View.OnClickListener() {
            @Overrideclea
            public void onClick(View view) {
                Intent intent = new Intent(Hizmetler.this, PeriyodikBakim.class);
                startActivity(intent);

            }
        });

        ButtonOnarimHizmetleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hizmetler.this, OnarimHizmetleri.class);
                startActivity(intent);

            }
        });

        ButtonBoyamaKaporta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hizmetler.this, BoyamaKaporta.class);
                startActivity(intent);

            }
        });


    }


}