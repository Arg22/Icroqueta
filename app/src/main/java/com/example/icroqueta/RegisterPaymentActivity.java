package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class RegisterPaymentActivity extends MenuBar {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_payment);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    public void ValidadrPago(View view) {
        //Validamos el pago// popup de exito y volver a home

        intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openBack(View view) {
        intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}