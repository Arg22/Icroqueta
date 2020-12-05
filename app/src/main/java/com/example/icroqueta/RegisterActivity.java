package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.DBSource;
import com.example.icroqueta.database.tablas.PersonaTable;

public class RegisterActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText contrasena;
    private EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void openLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void comprobarDatos(View view) {
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        contrasena = findViewById(R.id.contrasena);
        correo = findViewById(R.id.correo);


        Toast toast = Toast.makeText(getApplicationContext(),
                "Rellene los campos vacios", Toast.LENGTH_SHORT);

        //todo: comprobar datos y en caso afirmativo ir al Login
        if (nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty() ||contrasena.getText().toString().isEmpty() ||correo.getText().toString().isEmpty()){
            toast.show();
          }else{
            DBHelper db = new DBHelper();
            if(!(db.addUsuario(this,nombre.getText().toString(), apellido.getText().toString(),correo.getText().toString(),contrasena.getText().toString()))){
                Toast toast2 = Toast.makeText(getApplicationContext(),
                        "El correo ya está registrado", Toast.LENGTH_SHORT);
                toast2.show();
            }else{
                openLogin(view); //en caso afirmativo abrimos home
            }

        }

    }


    public void openBack(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}