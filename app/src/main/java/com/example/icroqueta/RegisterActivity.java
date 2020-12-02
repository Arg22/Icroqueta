package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    public void comprobarDatos(View view) {
        //todo: comprobar datos y en caso afirmativo ir al Login
        openLogin(view); //en caso afirmativo abrimos home
    }


    public void openBack(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}