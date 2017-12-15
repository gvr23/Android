package dsbmobile.com.app.practicadatabase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dsbmobile.com.app.practicadatabase.Model.Credencial;
import dsbmobile.com.app.practicadatabase.Persistent.MySQLLiteHelper;
import dsbmobile.com.app.practicadatabase.R;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.etUser)
    EditText etUser;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.btnLogeo)
    Button btnLogeo;

    MySQLLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        db = new MySQLLiteHelper(this);
        inicializar();

    }

    private void inicializar() {
        final String blockCharacteerSetPassword = "~`!@#$%^&*()_-\\|/?";
        etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPass.setFilters(new InputFilter[]{isPasswordValid(blockCharacteerSetPassword)});
        db.deleteTable();
        db.addUsuario(new Credencial("Zico@gmail.com", "2347"));
        db.addUsuario(new Credencial("jesusHuanachin@gmail.com", "2345"));
        db.addUsuario(new Credencial("Victor@gmail.com", "9876"));
        db.addUsuario(new Credencial("Wilfredo@gmail.com", "23457"));
        db.addUsuario(new Credencial("Josue@gmail.com", "73456"));
        db.addUsuario(new Credencial("Jose@gmail.com", "87456"));
        db.addUsuario(new Credencial("Giovani@gmail.com", "12345"));

        Stetho.initializeWithDefaults(this);

        eventos();
    }

    private void eventos() {
        etUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (isEmailValid(etUser.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalida", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    //support methods====================================
    public InputFilter isPasswordValid(final String specialCharacter) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null && specialCharacter.contains("" + source)) {
                    return "";
                }
                return null;
            }
        };
        return filter;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @OnClick(R.id.btnLogeo)
    public void onViewClicked() {
        //1. Obtener Strings de edit texts y el credencial de la base de datos
        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();
        Credencial credencial = db.getCredencial(user);

        //verifica si es que el usuario existe en la base de datos
        if (credencial != null && user.equals(credencial.getUsuario().toString()) && pass.equals(credencial.getContrasena().toString())) {
            Toast.makeText(getApplicationContext(), "Existe", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, CredencialesListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Usuario No Existe", Toast.LENGTH_LONG).show();
        }
    }
}
