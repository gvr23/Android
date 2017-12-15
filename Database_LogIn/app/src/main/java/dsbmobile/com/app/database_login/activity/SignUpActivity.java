package dsbmobile.com.app.database_login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dsbmobile.com.app.database_login.model.Credenciales;
import dsbmobile.com.app.database_login.persistent.MySQLiteHelper;
import dsbmobile.com.app.database_login.R;
import dsbmobile.com.app.database_login.utils.SupportMethods;

/**
 * Created by giova on 11/12/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    EditText etNuevoUsuario;
    EditText etNuevaContrasena;
    EditText etDuplicadoContrasena;
    EditText etNuevoPin;
    Button   btnSignUp;

    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new MySQLiteHelper(this);

        findViews();
        inicializar();
    }

    private void findViews() {
        etNuevoUsuario = (EditText) findViewById(R.id.etIngresaUsuario);
        etNuevaContrasena = (EditText) findViewById(R.id.etIngresaContrasena);
        etDuplicadoContrasena = (EditText) findViewById(R.id.etNuevamenteContrasena);
        etNuevoPin = (EditText) findViewById(R.id.etNuevoPin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
    }
    //chequear que mismo usuario no exista

    private void inicializar() {
        //para el usuario
        etNuevoUsuario.setHint("xxx@xxx.xxx");
        validarUsuario();

        etNuevaContrasena.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etDuplicadoContrasena.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //Filtros
        etNuevaContrasena.setFilters(new InputFilter[]{SupportMethods.isValid(SupportMethods.BLOCKCHARACTERSETPASSWORD)});
        etNuevoPin.setFilters(new InputFilter[]{SupportMethods.isValid(SupportMethods.BLOCKABC)});
        comparaciondeContrasenas();
    }

//    support methods===========================================================================================
    private void validarUsuario() {
        etNuevoUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (SupportMethods.isEmailValid(etNuevoUsuario.getText().toString())){
                    Toast.makeText(getBaseContext(), "Valid", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void comparaciondeContrasenas(){
        etDuplicadoContrasena.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //get duplicadocontrasena
                String duplicado = etDuplicadoContrasena.getText().toString();
                String contrasena = etNuevaContrasena.getText().toString();
                //comparar
                if (!contrasena.equals(duplicado)){
                    etDuplicadoContrasena.setText("");
                    etDuplicadoContrasena.setHint("Ingrese la contrasena Nuevamente");

//                    set focus
                    /*etDuplicadoContrasena.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);*/
                }
            }
        });
    }

    public void ingresoCredenciales(View view){
            String nuevoUsuario = etNuevoUsuario.getText().toString();
            String nuevoPassword = etNuevaContrasena.getText().toString();
            String duplicadoContrasena = etDuplicadoContrasena.getText().toString();
            String nuevoPin = etNuevoPin.getText().toString();

                if (!nuevoUsuario.equals("") && !nuevoPassword.equals("") && !duplicadoContrasena.equals("") && !nuevoPin.equals("")){
                    Credenciales credencial = new Credenciales();
                    credencial.setUsuario(nuevoUsuario);
                    credencial.setContrasena(nuevoPassword);
                    credencial.setPin(nuevoPin);

                    db.addUsuario(credencial);

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getBaseContext(), "Complete sus credenciales.", Toast.LENGTH_LONG);
                }
    }
}