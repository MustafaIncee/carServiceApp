package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Randevu extends AppCompatActivity {

    EditText textInputEditTextPlaka, TextInputEditTextKilometre;

    Button buttonRandevuOlustur;

    Spinner spinnerMarka, spinnerModel, spinnerYil ,spinnerYakit;

    Map<String, ArrayList<String>> markaModelMap;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu);

        Map<String, ArrayList<String>> markaModelMap;

        buttonRandevuOlustur = (Button)findViewById(R.id.randevuOlustur);

        spinnerMarka = findViewById(R.id.marka);
        spinnerModel = findViewById(R.id.model);
        spinnerYil = findViewById(R.id.yil);
        spinnerYakit = findViewById(R.id.yakit);

        textInputEditTextPlaka = findViewById(R.id.plaka);
        TextInputEditTextKilometre = findViewById(R.id.kilometre);

        initializeMarkaModelMap();
        setupMarkaSpinner();


        buttonRandevuOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marka, model, yil, plaka, kilometre, yakit;
                marka = spinnerMarka.getSelectedItem().toString();
                model = spinnerModel.getSelectedItem().toString();
                yil = spinnerYil.getSelectedItem().toString();
                plaka = String.valueOf(textInputEditTextPlaka.getText());
                kilometre = String.valueOf(TextInputEditTextKilometre.getText());
                yakit = spinnerYakit.getSelectedItem().toString();



                if (!marka.equals("") && !model.equals("") && !yil.equals("") && !plaka.equals("") && !kilometre.equals("") && !yakit.equals("")){
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
                            field[2] = "yil";
                            field[3] = "plaka";
                            field[4] = "kilometre";
                            field[5] = "yakit";


                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = marka;
                            data[1] = model;
                            data[2] = yil;
                            data[3] = plaka;
                            data[4] = kilometre;
                            data[5] = yakit;

                            PutData putData = new PutData("http://192.168.0.29/LoginRegister/CarInformation.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Arac Kaydi Basarili!"))
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

    private void initializeMarkaModelMap() {
        markaModelMap = new HashMap<>();
        ArrayList<String> audiModels = new ArrayList<>();
        audiModels.add("A3");
        audiModels.add("A4");
        audiModels.add("A6");
        audiModels.add("Q3");
        audiModels.add("Q5");
        audiModels.add("Q7");

        ArrayList<String> bmwModels = new ArrayList<>();
        bmwModels.add("1 Serisi");
        bmwModels.add("2 Serisi");
        bmwModels.add("3 Serisi");
        bmwModels.add("4 Serisi");
        bmwModels.add("5 Serisi");
        bmwModels.add("6 Serisi");
        bmwModels.add("7 Serisi");
        bmwModels.add("X1");
        bmwModels.add("X2");
        bmwModels.add("X3");
        bmwModels.add("X5");
        bmwModels.add("X6");

        ArrayList<String> fordModels = new ArrayList<>();
        fordModels.add("Fiesta");
        fordModels.add("Focus");
        fordModels.add("Mondeo");
        fordModels.add("Kuga");

        ArrayList<String> hondaModels = new ArrayList<>();
        hondaModels.add("Civic");
        hondaModels.add("Accord");
        hondaModels.add("HR-V");
        hondaModels.add("CR-V");

        ArrayList<String> opelModels = new ArrayList<>();
        opelModels.add("Astra");
        opelModels.add("Corsa");
        opelModels.add("Insignia");

        ArrayList<String> toyotaModels = new ArrayList<>();
        toyotaModels.add("Corolla");
        toyotaModels.add("Yaris");
        toyotaModels.add("RAV4");
        toyotaModels.add("Highlander");
        toyotaModels.add("Prius");

        ArrayList<String> volkswagenModels = new ArrayList<>();
        volkswagenModels.add("Golf");
        volkswagenModels.add("Passat");
        volkswagenModels.add("Polo");
        volkswagenModels.add("Tiguan");
        volkswagenModels.add("Jetta");

        ArrayList<String> citroenModels = new ArrayList<>();
        citroenModels.add("C3");
        citroenModels.add("C4");
        citroenModels.add("C5");
        citroenModels.add("Cactus");

        ArrayList<String> daciaModels = new ArrayList<>();
        daciaModels.add("Sandero");
        daciaModels.add("Logan");
        daciaModels.add("Duster");
        daciaModels.add("Lodgy");
        daciaModels.add("Dokker");

        ArrayList<String> fiatModels = new ArrayList<>();
        fiatModels.add("500");
        fiatModels.add("Egea");
        fiatModels.add("Panda");
        fiatModels.add("Tipo");
        fiatModels.add("Doblo");

        ArrayList<String> hyundaiModels = new ArrayList<>();
        hyundaiModels.add("i10");
        hyundaiModels.add("i20");
        hyundaiModels.add("i30");
        hyundaiModels.add("Kona");
        hyundaiModels.add("Tucson");

        ArrayList<String> kiaModels = new ArrayList<>();
        kiaModels.add("Picanto");
        kiaModels.add("Rio");
        kiaModels.add("Ceed");
        kiaModels.add("Sportage");
        kiaModels.add("Sorento");


        ArrayList<String> mercedesModels = new ArrayList<>();
        mercedesModels.add("A-Class");
        mercedesModels.add("C-Class");
        mercedesModels.add("E-Class");
        mercedesModels.add("S-Class");
        mercedesModels.add("GLE");
        mercedesModels.add("GLC");

        ArrayList<String> nissanModels = new ArrayList<>();
        nissanModels.add("Micra");
        nissanModels.add("Juke");
        nissanModels.add("Qashqai");
        nissanModels.add("X-Trail");
        nissanModels.add("Leaf");

        ArrayList<String> peugeotModels = new ArrayList<>();
        peugeotModels.add("208");
        peugeotModels.add("308");
        peugeotModels.add("508");
        peugeotModels.add("2008");
        peugeotModels.add("3008");
        peugeotModels.add("5008");

        ArrayList<String> renaultModels = new ArrayList<>();
        renaultModels.add("Clio");
        renaultModels.add("Megane");
        renaultModels.add("Captur");
        renaultModels.add("Kadjar");
        renaultModels.add("Talisman");
        renaultModels.add("Koleos");


        ArrayList<String> volvoModels = new ArrayList<>();
        volvoModels.add("S60");
        volvoModels.add("S90");
        volvoModels.add("XC40");
        volvoModels.add("XC60");
        volvoModels.add("XC90");



        markaModelMap.put("Volvo", volvoModels);
        markaModelMap.put("Nissan", nissanModels);
        markaModelMap.put("Peugeot", peugeotModels);
        markaModelMap.put("Renault", renaultModels);
        markaModelMap.put("Mercedes", mercedesModels);
        markaModelMap.put("Kia", kiaModels);
        markaModelMap.put("Hyundai", hyundaiModels);
        markaModelMap.put("Fiat", fiatModels);
        markaModelMap.put("Citroen", citroenModels);
        markaModelMap.put("Audi", audiModels);
        markaModelMap.put("BMW", bmwModels);
        markaModelMap.put("Ford", fordModels);
        markaModelMap.put("Honda", hondaModels);
        markaModelMap.put("Toyota", toyotaModels);
        markaModelMap.put("Volkswagen", volkswagenModels);
        markaModelMap.put("Dacia", daciaModels);
        markaModelMap.put("Opel", opelModels);

    }

    private void setupMarkaSpinner() {
        ArrayAdapter<String> markaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(markaModelMap.keySet()));
        markaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarka.setAdapter(markaAdapter);

        spinnerMarka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMarka = markaAdapter.getItem(position);
                setupModelSpinner(selectedMarka);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupModelSpinner(String marka) {
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, markaModelMap.get(marka));
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(modelAdapter);
    }

}
