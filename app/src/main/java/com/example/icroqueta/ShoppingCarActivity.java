package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class ShoppingCarActivity extends MenuBar {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shopping_car);
        //todo recicle view con los productos con una cantidad >0
        //todo mostrar el total de todo el precio
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void openPagar(View view) {
        //Comprobar los datos de registro si no los tiene se habre register
          intent= new Intent(this, RegisterPaymentActivity.class);
        startActivity(intent);
    }
}