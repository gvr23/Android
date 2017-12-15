package dsbmobile.com.app.database_login.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dsbmobile.com.app.database_login.adapter.CredencialesAdapter;
import dsbmobile.com.app.database_login.model.Credenciales;
import dsbmobile.com.app.database_login.persistent.MySQLiteHelper;
import dsbmobile.com.app.database_login.R;

/**
 * Created by giova on 11/10/2017.
 */

public class CredencialesListActivity extends AppCompatActivity {
    ListView lvCred;
    ArrayList<Credenciales> arrayOfCredenciales;
    MySQLiteHelper db;
    CredencialesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MySQLiteHelper(this);
        setContentView(R.layout.activity_credenciales_list);
        findViews();
        populatingArray();
        inicializar();
        getItem();
    }


    private void findViews() {
        lvCred = (ListView) findViewById(R.id.lvCredenciales);
    }

    private void inicializar() {
        adapter = new CredencialesAdapter(this, R.layout.row_credencial ,arrayOfCredenciales);
        lvCred.setAdapter(adapter);
    }

    private void populatingArray() {
        arrayOfCredenciales = db.getAllCredenciales();
    }

    public void getItem(){
        lvCred.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CredencialesListActivity.this, arrayOfCredenciales.get(position).getUsuario(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
