package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;


public class LoginActivity extends AppCompatActivity {
    Intent intent;
    private EditText correo;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openRegistro(View view) {
        intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openPrincipal(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void comprobarDatos(View view) {
        //todo: comprobar datos y en caso afirmativo abrir Home
        contrasena = findViewById(R.id.password_login);
        correo = findViewById(R.id.correo_login);


        if (contrasena.getText().toString().isEmpty() || correo.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Rellene los campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper db = new DBHelper();
            switch (db.findUsuario(this, correo.getText().toString(), contrasena.getText().toString())) {
                case -1:
                    Toast.makeText(getApplicationContext(),
                            "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(getApplicationContext(),
                            "Correo no registrado", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    openPrincipal(view); //en caso afirmativo abrimos home
                    //todo enviar id y rol
                    break;
            }

        }

    }

    public void pedirContrasena(View view) {
        //todo: pedir contraseña usuario
    }
}