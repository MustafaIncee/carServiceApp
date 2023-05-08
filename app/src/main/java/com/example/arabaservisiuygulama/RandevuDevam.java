package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RandevuDevam extends AppCompatActivity {

     EditText phoneNumber;
     DatePicker datePicker;
     TimePicker timePicker;
     Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu_devam);

        phoneNumber = findViewById(R.id.telefon);
        datePicker = findViewById(R.id.tarih);
        timePicker = findViewById(R.id.saat);
        submitButton = findViewById(R.id.randevu_onay);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = String.valueOf(phoneNumber.getText());
                String day = String.valueOf(datePicker.getDayOfMonth());
                String month = String.valueOf(datePicker.getMonth());
                String year = String.valueOf(datePicker.getYear());
                String hour = String.valueOf(timePicker.getHour());
                //int minute = timePicker.getMinute();

                String message = "Randevu Bilgileri:\nTelefon Numarası: " + phone + "\nTarih: " + day + "/" + (month + 1) + "/" + year + "\nSaat: " + hour + ":";
                Toast.makeText(RandevuDevam.this, message, Toast.LENGTH_LONG).show();

                if (!phone.equals("") && !day.equals("") && !month.equals("") && !year.equals("") && !hour.equals("")){
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "phone";
                            field[1] = "day";
                            field[2] = "month";
                            field[3] = "year";
                            field[4] = "hour";


                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = phone;
                            data[1] = day;
                            data[2] = month;
                            data[3] = year;
                            data[4] = hour;

                            PutData putData = new PutData("http://192.168.0.29/LoginRegister/Randevu.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Randevu Olusturuldu!"))
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
                    Toast.makeText(getApplicationContext(), "Gerekli alanlar doldurulmalı", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}