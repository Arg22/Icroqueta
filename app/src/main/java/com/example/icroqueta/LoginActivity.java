package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Persona;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    private EditText correo;
    private EditText contrasena;
    public static Persona  usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openRegistro(View view) {
        intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    public void comprobarDatos(View view) {
        contrasena = findViewById(R.id.password_login);
        correo = findViewById(R.id.correo_login);
        if (contrasena.getText().toString().isEmpty() || correo.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Rellene los campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper db = new DBHelper();
            int idPersona = db.findPersonaLogin(this, correo.getText().toString(), contrasena.getText().toString());
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
                    usuario= db.findPersonaId(this,idPersona);
                    startActivity(intent);
                    break;
            }
        }
    }

    public void pedirContrasena(View view) {
        //todo: pedir contraseña usuario
        Toast.makeText(getApplicationContext(),
                "Pues haberla apuntado", Toast.LENGTH_SHORT).show();
    }
}