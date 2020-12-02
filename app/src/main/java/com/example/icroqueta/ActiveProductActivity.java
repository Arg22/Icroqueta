package com.example.icroqueta;

import android.os.Bundle;

import java.util.Objects;

public class ActiveProductActivity extends MenuBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_product);
        //todo: adapter con los prodcutos activos

        //todo: meter mapa google

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() { //La accion del botón
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}