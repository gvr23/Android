package dsbmobile.com.app.retrowilf.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsbmobile.com.app.retrowilf.Adapter.ShowAdapter;
import dsbmobile.com.app.retrowilf.R;
import dsbmobile.com.app.retrowilf.model.Credencial;

/**
 * Created by giova on 11/15/2017.
 */

public class ShowActivity extends Activity {
    @BindView(R.id.rvCredencialesList)
    RecyclerView rvCredencialesList;

    ArrayList<Credencial> listaCredenciales;
    ShowAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_credenciales_list);
        ButterKnife.bind(this);
        populatingArray();
        inicializar();
    }

    private void populatingArray() {
        Bundle bundle = this.getIntent().getExtras();
        listaCredenciales = bundle.getParcelableArrayList("items");
        Log.i("test", "pip");
    }

    private void inicializar() {
        sAdapter = new ShowAdapter(listaCredenciales, getBaseContext(), R.layout.row_credencial_item);
        rvCredencialesList.setAdapter(sAdapter);
        rvCredencialesList.setLayoutManager( new LinearLayoutManager(this));
    }
}
