package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void openLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //todo - Validacion de datos
    public void comprobarDatos(View view) {
        EditText nif = findViewById(R.id.nif);
        EditText nombre = findViewById(R.id.nombre);
        EditText apellido = findViewById(R.id.apellido);
        EditText contrasena = findViewById(R.id.contrasena);
        EditText correo = findViewById(R.id.correo);

        //comprueba datos y en caso afirmativo abre el Login
        if (nif.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty() || contrasena.getText().toString().isEmpty() || correo.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Rellene los campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper db = new DBHelper();

            if (!(db.addPersona(this, nif.getText().toString(), nombre.getText().toString(), apellido.getText().toString(), correo.getText().toString(), contrasena.getText().toString()))) {
                Toast.makeText(getApplicationContext(),
                        "El correo ya está registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registro completado",Toast.LENGTH_LONG).show();
                openLogin(view); //en caso afirmativo abrimos home
            }
        }
    }


    public void openBack(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}