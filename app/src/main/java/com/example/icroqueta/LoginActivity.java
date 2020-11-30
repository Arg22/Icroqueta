package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LoginActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openRegistro(View view) {
         intent= new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openPrincipal(View view) {
           intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void comprobarDatos(View view) {
        //Aqui se recogerian los datos con la base de datos y comprobaria

        openPrincipal(view); //en caso afirmativo abrimos home
    }

    public void pedirContrasena(View view) {
        //Aqui se pide la contraseña de recuperacion
    }
}