package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;

import java.util.Objects;

public class OptionActivity extends MenuBar {
    long backPressedTime;
    private static final long TIME_TO_CLOSE_APP = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        //todo Futuro - Cargar información usario
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    public void guardarOpcion(View view) {
        //todo Futuro - Guardar información usuario en la base de datos correspondiente
    }

    public void borrarCuenta(View view) {
        long  time = System.currentTimeMillis();

        if (time - backPressedTime > TIME_TO_CLOSE_APP) {
            backPressedTime = time;
            Toast.makeText(this, "Pulse dos veces para borrar cuenta",Toast.LENGTH_LONG).show();
        } else {
            DBHelper db =new DBHelper();
            db.deletePersona(this,LoginActivity.usuario.getIdPersona());
        }
    }
}