package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProductSummaryActivity extends MenuBar {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_summary);
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