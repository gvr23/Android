package dsbmobile.com.app.servicesdatabase.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import dsbmobile.com.app.servicesdatabase.Credencial;
import dsbmobile.com.app.servicesdatabase.R;
import dsbmobile.com.app.servicesdatabase.network.ApiClient;
import dsbmobile.com.app.servicesdatabase.network.ApiInterface;
import dsbmobile.com.app.servicesdatabase.network.response.CredencialResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//how to use rertofit
/*1. dependencies
2. Endpoints
3. JSON Mapping
4. APICllient
5. Execute requests and display data*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetUsuariosTask task = new GetUsuariosTask();
        task.execute();
    }

    class GetUsuariosTask extends AsyncTask<Void, Void, CredencialResponse>{

        @Override
        protected CredencialResponse doInBackground(Void... voids) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CredencialResponse> call = apiInterface.getUsers();
            call.enqueue(new Callback<CredencialResponse>() {
                @Override
                public void onResponse(Call<CredencialResponse> call, Response<CredencialResponse> response) {
                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(this);
                    Credencial credencial = gson.fromJson(reader, Credencial.class);
                    CredencialResponse credencialResponse = response.body();

                    Log.i("id", String.valueOf(credencialResponse.getId()));
                    Log.i("usuario", String.valueOf(credencialResponse.getUsuario()));
                    Log.i("contrasena", String.valueOf(credencialResponse.getContrasena()));
                    Log.i("pin", String.valueOf(credencialResponse.getPin()));

                }

                @Override
                public void onFailure(Call<CredencialResponse> call, Throwable t) {
                    String entero = "este";
                    Log.i("test", t.toString());
                }
            });
            return null;
        }
    }
}
