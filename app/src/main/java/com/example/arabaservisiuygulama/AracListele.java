package com.example.arabaservisiuygulama;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class AracListele extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_listele);

        listView = findViewById(R.id.list_view);

        new FetchData().execute();
    }

    private class FetchData extends AsyncTask<Void, Void, Void> {

        ArrayList<String> data = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.0.29/LoginRegister/MyCars.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while (line != null) {
                    line = bufferedReader.readLine();
                    stringBuilder.append(line);
                }
                String response = stringBuilder.toString();

                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String marka = jsonObject.getString("marka");
                    String kullanici = jsonObject.getString("kullanici_id");
                    String kullanici2 = jsonObject.getString("kullaniciAd");
                    String kullanici3 = jsonObject.getString("kullaniciSoyad");
                    String model = jsonObject.getString("model");
                    String yil = jsonObject.getString("yil");
                    String plaka = jsonObject.getString("plaka");
                    String kilometre = jsonObject.getString("kilometre");
                    String yakit = jsonObject.getString("yakit");

                    String item = "id: "+ kullanici + " " + "Ad Soyad: " + kullanici2 + " " + kullanici3 + "\n" +"Araç Bilgileri:" + " " + marka + " " + model + " (" + yil + ") - " + plaka + " " + " \n" + kilometre + " km - " + yakit;
                    data.add(item);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(AracListele.this, android.R.layout.simple_list_item_1, data);
            listView.setAdapter(adapter);
        }
    }
}