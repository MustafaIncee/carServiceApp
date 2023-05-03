package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ButtonCarInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonCarInformation = findViewById(R.id.button1);
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
    }
}
