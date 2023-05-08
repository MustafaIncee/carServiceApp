package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ButtonCarInformation, ButtonIletisim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonIletisim = findViewById(R.id.button4);
        ButtonCarInformation = findViewById(R.id.button1);

        ButtonIletisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IletisimBilgilerimiz.class);
                startActivity(intent);
                finish();
            }
        });
        ButtonCarInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Randevu.class);
                startActivity(intent);
                finish();
            }
        });
    }

}

