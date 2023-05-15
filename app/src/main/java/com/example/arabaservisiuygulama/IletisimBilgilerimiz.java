package com.example.arabaservisiuygulama;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IletisimBilgilerimiz extends AppCompatActivity {

    private TextView adres1TextView, adres2TextView,adres3TextView,telefon1TV,telefon2TV,telefon3TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim_bilgilerimiz);

        adres1TextView = findViewById(R.id.adres1);
        adres2TextView = findViewById(R.id.adres2);
        adres3TextView = findViewById(R.id.adres3);
        telefon1TV = findViewById(R.id.telefon1);
        telefon2TV = findViewById(R.id.telefon2);
        telefon3TV = findViewById(R.id.telefon3);

        // Verileri almak için AsyncTask'i başlat
        new FetchDataTask().execute();
    }

    private class FetchDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonStr = null;

            try {
                // Verileri almak istediğiniz PHP dosyasının URL'sini belirtin
                URL url = new URL("http://192.168.0.29/LoginRegister/Adres.php");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }

                if (builder.length() == 0) {
                    return null;
                }

                jsonStr = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray jsonArray = new JSONArray(s);

                // İlk iki adresi TextView'lere yazdır
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                adres1TextView.setText(jsonObject1.getString("adres"));

                JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                adres2TextView.setText(jsonObject2.getString("adres"));

                JSONObject jsonObject3 = jsonArray.getJSONObject(2);
                adres3TextView.setText(jsonObject3.getString("adres"));


                JSONObject jsonObject4 = jsonArray.getJSONObject(0);
                telefon1TV.setText(jsonObject4.getString("telefon"));

                JSONObject jsonObject5 = jsonArray.getJSONObject(1);
                telefon2TV.setText(jsonObject5.getString("telefon"));

                JSONObject jsonObject6 = jsonArray.getJSONObject(2);
                telefon3TV.setText(jsonObject6.getString("telefon"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}