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
        //todo: comprobar datos que se tienen del usuario y pedir los que les falten
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() { //Accion botón retroceder
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    //todo: checkbox enviar informacion a las propiedades

    public void ValidadrPago(View view) {
        //Validamos el pago// popup de exito y volver a home
        //todo: si está todo correcto sale un popup que avisa de que todo está correcto
        //todo: enviar datos bd = crear pedido activo
        openBack(view);
    }

    public void openBack(View view) {
        intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}