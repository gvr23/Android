package dsbmobile.com.app.practicadatabase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsbmobile.com.app.practicadatabase.Adapters.CredencialesAdapter;
import dsbmobile.com.app.practicadatabase.Model.Credencial;
import dsbmobile.com.app.practicadatabase.Persistent.MySQLLiteHelper;
import dsbmobile.com.app.practicadatabase.R;

/**
 * Created by giova on 11/12/2017.
 */

public class CredencialesListActivity extends AppCompatActivity {


    ArrayList<Credencial> credencialArrayList;
    MySQLLiteHelper db;
    CredencialesAdapter adapter;
    @BindView(R.id.lvCrenceciales)
    ListView lvCrenceciales;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MySQLLiteHelper(this);
        setContentView(R.layout.activity_credenciales_list);
        ButterKnife.bind(this);


        inicializar();
    }

    private void inicializar() {
        populatingArray();
        adapter = new CredencialesAdapter(getBaseContext(), R.layout.row_credencial, credencialArrayList);
        lvCrenceciales.setAdapter(adapter);
        getItem();
    }

    private void populatingArray() {
        credencialArrayList = db.getCredenciales();
    }

    private void getItem() {
        lvCrenceciales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CredencialesListActivity.this, credencialArrayList.get(position).getUsuario(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
