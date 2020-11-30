package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class ProductSummaryActivity extends MenuBar {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_summary);
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

    public void openAtras(View view) {
        intent= new Intent(this, ShoppingCarActivity.class);
        startActivity(intent);
    }
}