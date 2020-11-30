package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }


    public void openLogin(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openBack(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}