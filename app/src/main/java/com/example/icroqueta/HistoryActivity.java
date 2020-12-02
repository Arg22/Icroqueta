package com.example.icroqueta;

import android.os.Bundle;

import java.util.Objects;

public class HistoryActivity extends MenuBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //todo: Lista de los pedidos arcivados
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {//Accion botón
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}