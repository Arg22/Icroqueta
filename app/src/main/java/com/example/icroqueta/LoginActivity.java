package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    private EditText correo;
    private EditText contrasena;
    private CheckBox checkRecordad;

    //todo secundario - optimizar la manera de enviar esta informacion a las demas activties

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contrasena = findViewById(R.id.password_login);
        correo = findViewById(R.id.correo_login);
        checkRecordad = findViewById(R.id.recordarDatosLogin);
        cargarPreferencias();
    }


    /**
     * Se abre el registro al pulsar en el botón
     *
     * @param view nuestra view
     */
    public void openRegistro(View view) {
        intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Método para comprobar los datos cuando el usuario se logea.
     *
     * @param view nuestra view
     */
    public void comprobarDatos(View view) {

        if (contrasena.getText().toString().isEmpty() || correo.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Rellene los campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper db = new DBHelper();
            //Para encripar la contraseña
            String passEncriptada = new String(Hex.encodeHex(DigestUtils.sha1(contrasena.getText().toString())));
            int idPersona = db.findPersonaLogin(this, correo.getText().toString().toLowerCase(), passEncriptada);
            switch (idPersona) {
                case -1:
                    Toast.makeText(getApplicationContext(),
                            "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(getApplicationContext(),
                            "Correo no registrado", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    //Aqui guardaremos la id del usuario que acaba de abrir sesión
                    guardarUsuario();
                    break;
            }
        }
    }

    /**
     * Método para cargar las preferencias si el usuario pulsó el botón de recordar datos
     */
    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        boolean recordar = preferences.getBoolean("recordar_login", false);
        if (recordar) {
            String user = preferences.getString("email", "");
            String pass = preferences.getString("pass", "");
            correo.setText(user);
            contrasena.setText(pass);
            checkRecordad.setChecked(true);
        }
    }

    /**
     * Método para guardar las credenciales del usuario
     */
    private void guardarUsuario() {
        DBHelper db = new DBHelper();
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String email = correo.getText().toString();
        String pass = contrasena.getText().toString();
        String passEncriptada = new String(Hex.encodeHex(DigestUtils.sha1(contrasena.getText().toString())));
        int id = db.findPersonaLogin(this, correo.getText().toString().toLowerCase(), passEncriptada);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("pass", pass);
        editor.putInt("id", id);
        if (checkRecordad.isChecked()) {
            editor.putBoolean("recordar_login", true);
        } else {
            editor.putBoolean("recordar_login", false);
        }

        editor.apply();
    }

    /**
     * Método para devolverle la contraseña a un usuario
     *
     * @param view nuestra view
     */
    public void pedirContrasena(View view) {
        //todo secundario enviar correo con la contraseña al usuario
        Toast.makeText(getApplicationContext(),
                "Pues haberla apuntado", Toast.LENGTH_SHORT).show();
    }
}