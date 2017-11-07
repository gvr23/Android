package dsbmobile.com.app.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    EditText etCity;
    TextView tvWeather;
    Button btnFind;

    String encodedCity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        eventos();

    }

    private void findViews() {
        etCity = (EditText) findViewById(R.id.etCity);
        tvWeather = (TextView) findViewById(R.id.tvWeather);
        btnFind = (Button) findViewById(R.id.btnFind);
    }

    private void inicializar() {
    }

    private void eventos() {
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //to be able to put city names with spaces
                    encodedCity = URLEncoder.encode(etCity.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (encodedCity.length() != 0){
                    DownloadTask task = new DownloadTask();
                    //gets the actual input method that the user is using which is the keyboard
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //hide the keyboard
                    mgr.hideSoftInputFromWindow(etCity.getWindowToken(),0);
                    task.execute("http://api.openweathermap.org/data/2.5/forecast?q="+encodedCity+"&units=metric&APPID=6e2cf25e187178b2681e865ac8c4694a");
                }else{
                    Toast.makeText(getApplicationContext(), "Enter a city", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection connection = null;

            try{
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){
                    char current = (char) data;

                    result += current;
                    data = reader.read();
                }

                return result;
            }catch(Exception e){
                Log.i("exception", "wrong city");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    String message = "";

                    JSONObject jsonObject = new JSONObject(result);
                    //para obtener el arreglo que contiene weather y escoger una de las tantas opciones.
                    JSONArray array = jsonObject.getJSONArray("list");
                    JSONObject jObject = array.getJSONObject(0);
                    //para extraer weather de la opción que elegimos.
                    String weatherInfo = jObject.getString("weather");
                    //para poder extraer las partes que queremos
                    JSONArray arr = new JSONArray(weatherInfo);
                    String main = "";
                    String description = "";


                    JSONObject jsonPart = arr.getJSONObject(0);

                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if (main != "" && description != ""){
                        message += main + ": " + description + "\r\n";
                    }else{
                        Toast.makeText(getApplicationContext(), "Ciudad no existe", Toast.LENGTH_LONG).show();
                    }

                    if (message != ""){
                        tvWeather.setText(message);
                    }else{
                        Toast.makeText(getApplicationContext(), "Ciudad no existe", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Ciudad no existe", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Ciudad no existe", Toast.LENGTH_LONG).show();
            }
        }
    }
}
