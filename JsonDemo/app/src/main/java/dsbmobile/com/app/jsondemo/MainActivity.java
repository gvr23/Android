package dsbmobile.com.app.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();
    }

    private void inicializar() {
        DownloadTask task = new DownloadTask();
        task.execute("http://api.openweathermap.org/data/2.5/forecast?q=Lima&units=metric&APPID=6e2cf25e187178b2681e865ac8c4694a");
    }

    //    pass a class a string, and return a string as well
    public class DownloadTask extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();

            InputStream in = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
//            int variable to read the contents of our reader
            int data = reader.read();

            while (data != -1){
                char current = (char) data;
                result += current;
                    data = reader.read();
            }

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray("list");
            JSONObject weatherInfo = array.getJSONObject(0);
            String weather = weatherInfo.getString("weather");
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    }
}
