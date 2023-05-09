package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button listele, sil;

        listele = findViewById(R.id.listeleButton);
        sil = findViewById(R.id.silButton);


        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, AracListele.class);
                startActivity(intent);
                finish();
            }
        });

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, AracSil.class);
                startActivity(intent);
                finish();
            }
        });


    }
}