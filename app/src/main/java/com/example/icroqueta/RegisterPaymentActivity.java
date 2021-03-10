package com.example.icroqueta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;

import java.util.Objects;

public class RegisterPaymentActivity extends MenuBar {
    Intent intent;
    int idPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_payment);
        //todo - comprobar datos que se tienen del usuario y pedir los que les falten
        cargarIdUsuario();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
        final TextView fecha = findViewById(R.id.fechaTarjetaPago);
        //Esto es para que escriba automaticamente en la fehca la barra lateral
        fecha.addTextChangedListener(new TextWatcher() {
            int first = 0;
            int second;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                second = first;
                first = s.length();
                if (fecha.getText().length() == 2 && first > second) {
                    fecha.append("/");
                }
            }

        });
    }

    @Override
    public boolean onSupportNavigateUp() { //Accion botón retroceder
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }



    //todo hash tarjeta
    //todo - Validacion de datos
    public void comprobarDatos(View view) {
        EditText telefono = findViewById(R.id.telefonoPago);
        EditText direccion = findViewById(R.id.direccionPago);
        EditText localidad = findViewById(R.id.localidadPago);
        EditText codigo_postal = findViewById(R.id.cPostalPago);
        EditText tarjeta = findViewById(R.id.tarjetaPago);
        EditText fecha = findViewById(R.id.fechaTarjetaPago);
        EditText cvc = findViewById(R.id.cvcPago);

        //comprueba datos y en caso afirmativo abre el Login
        if (telefono.getText().toString().isEmpty() || direccion.getText().toString().isEmpty() || tarjeta.getText().toString().isEmpty() || fecha.getText().toString().isEmpty() || cvc.getText().toString().isEmpty() || localidad.getText().toString().isEmpty() || codigo_postal.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Rellene los campos vacios", Toast.LENGTH_SHORT).show();
        } else {

            //todo -Comprueba los datos en la base de datos o los añade

            validarPago();
        }
    }

    public void validarPago() {
        //Se elimina el carro una vez hecho y se crea las lineas del producto asociado a un pedido
        DBHelper db = new DBHelper();
        if (db.addPedido(this, idPersona)) {
            Toast.makeText(getApplicationContext(), "Pago realizado con éxito", Toast.LENGTH_LONG).show();
            //Se va automáticamente al MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void openBack(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Método para sacar el id del usuario de las credenciales guardadas
     */
    private void cargarIdUsuario() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        idPersona = preferences.getInt("id", 0);
    }
}