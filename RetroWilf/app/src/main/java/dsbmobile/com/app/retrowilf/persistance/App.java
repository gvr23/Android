package dsbmobile.com.app.retrowilf.persistance;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

import dsbmobile.com.app.retrowilf.model.Credencial;
import dsbmobile.com.app.retrowilf.network.APIInterface;
import dsbmobile.com.app.retrowilf.network.ApiClient;
import dsbmobile.com.app.retrowilf.network.response.CredencialesResponse;
import dsbmobile.com.app.retrowilf.network.response.CredencialesResponseList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by giova on 11/16/2017.
 */

public class App extends Application {
    private MySQLiteHelper db;
    private ArrayList<Credencial> listCredenciales;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new MySQLiteHelper(this);
        db.getWritableDatabase();
        AsyncTasker tasker = new AsyncTasker();
        tasker.execute();

        Stetho.initializeWithDefaults(this);

    }

    public ArrayList<Credencial> getListCredenciales() {
        return listCredenciales;
    }

    class AsyncTasker extends AsyncTask<Void, Void, CredencialesResponseList> {

        @Override
        protected CredencialesResponseList doInBackground(Void... voids) {
            APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
            Call<CredencialesResponseList> call = apiInterface.getUsers();
            call.enqueue(new Callback<CredencialesResponseList>() {
                @Override
                public void onResponse(Call<CredencialesResponseList> call, Response<CredencialesResponseList> response) {
                    try {
                        listCredenciales = new ArrayList<>();

                        for (CredencialesResponse credencialesResponse : response.body().getCredencialesList()) {
                            Credencial credencial = new Credencial();
                            credencial.setId(Integer.parseInt(credencialesResponse.getId()));
                            credencial.setUsuario(credencialesResponse.getUsuario());
                            credencial.setContrasena(credencialesResponse.getContrasena());
                            credencial.setPin(Integer.parseInt(credencialesResponse.getPin()));
                            listCredenciales.add(credencial);
                            db.addUsuario(credencial);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<CredencialesResponseList> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "no hay usuarios", Toast.LENGTH_LONG).show();
                }
            });

            return null;
        }
    }

}

