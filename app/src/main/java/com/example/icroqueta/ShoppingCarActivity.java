package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShoppingCarActivity extends MenuBar {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shopping_car);
    }

    public void openResumen(View view) {
        intent= new Intent(this, ProductSummaryActivity.class);
        startActivity(intent);
    }
}