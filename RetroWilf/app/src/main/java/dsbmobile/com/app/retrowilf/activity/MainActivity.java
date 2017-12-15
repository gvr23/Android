package dsbmobile.com.app.retrowilf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dsbmobile.com.app.retrowilf.R;
import dsbmobile.com.app.retrowilf.model.Credencial;
import dsbmobile.com.app.retrowilf.persistance.App;

public class MainActivity extends Activity {

    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.etContrasena)
    EditText etContrasena;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;

    ArrayList<Credencial> listaCredenciales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignIn)
    public void onViewClicked() {
        App app = (App) getApplication();
        listaCredenciales = app.getListCredenciales();

        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("items", listaCredenciales);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
