package dsbmobile.com.app.database_login.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import dsbmobile.com.app.database_login.model.Credenciales;
import dsbmobile.com.app.database_login.persistent.MySQLiteHelper;
import dsbmobile.com.app.database_login.R;
import dsbmobile.com.app.database_login.utils.SupportMethods;

public class MainActivity extends AppCompatActivity {
    TextView tvSignupLink;
    TextView tvForgotPasswwordLink;
    EditText usuario;
    EditText password;
    Button btnLogIn;

    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MySQLiteHelper(this);
        db.addUsuario(new Credenciales("Giovani@gmail.com", "12345", "1234"));
        Stetho.initializeWithDefaults(this);

        fidViews();
        inicializar();
        logeo();
    }

    private void fidViews() {
        usuario = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPass);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        tvSignupLink = (TextView) findViewById(R.id.tvSignUpLink);
        tvForgotPasswwordLink = (TextView) findViewById(R.id.tvForgotPassword);
    }

    private void inicializar() {

//      para el edit text de password
        password.setHint("Password");
        //para el usuario
        usuario.setHint("xxx@xxx.xxx");
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //password filters
        password.setFilters(new InputFilter[]{SupportMethods.isValid(SupportMethods.BLOCKCHARACTERSETPASSWORD)});
        //user filters
        eventos();
    }

    private void eventos() {
        //usuario
        usuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (SupportMethods.isEmailValid(usuario.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //if the use wants to sign up.
        signUp();
    }



    public void logeo(){
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //primero obtener lo que el usuario tipeo
                String user = usuario.getText().toString();
                String pass = password.getText().toString();
                Credenciales credencial = db.getCredencial(user);
                //verificar con la base de datos si es que el record existe igual al pass y el usuario
                if (credencial != null && user.equals(credencial.getUsuario()) && pass.equals(credencial.getContrasena())){
                    Toast.makeText(getApplicationContext(), "Usuario Existe", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, CredencialesListActivity.class);
//                    intent.putExtra("key", value);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario No Existe", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void signUp(){
        tvSignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
