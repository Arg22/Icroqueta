package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;

import java.util.Objects;

public class OptionActivity extends MenuBar {
    long backPressedTime;
    EditText nombre;
    EditText apellido;
    EditText nif;
    EditText correo;
    EditText contrasena;
    EditText telefono;
    EditText direccion;
    EditText localidad;
    EditText codigoPostal;
    EditText tarjeta;
    EditText fechaTarjeta;

    private static final long TIME_TO_CLOSE_APP = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        cargarDatos();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    public void guardarOpcion(View view) {
        //todo Futuro - Guardar información usuario en la base de datos correspondiente
    }

    public void borrarCuenta(View view) {
        long time = System.currentTimeMillis();

        if (time - backPressedTime > TIME_TO_CLOSE_APP) {
            backPressedTime = time;
            Toast.makeText(this, "Pulse otra vez para borrar cuenta", Toast.LENGTH_LONG).show();
        } else {
            DBHelper db = new DBHelper();
            db.deletePersona(this, LoginActivity.usuario.getIdPersona());
            //Y sale al loggin
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }

    public void cargarDatos() {
        nombre = findViewById(R.id.nombreOpciones);
        apellido = findViewById(R.id.apellidoOpciones);
        nif = findViewById(R.id.nifOpciones);
        correo = findViewById(R.id.correoOpciones);
        contrasena = findViewById(R.id.contrasenaOpciones);
        telefono = findViewById(R.id.telefonoOpciones);
        direccion = findViewById(R.id.direccionOpciones);
        localidad = findViewById(R.id.localidadOpciones);
        codigoPostal = findViewById(R.id.cPostalOpciones);
        tarjeta = findViewById(R.id.tarjetaOpciones);
        fechaTarjeta = findViewById(R.id.fechaTarjetaOpciones);


        nombre.setText(LoginActivity.usuario.getNombre());
        apellido.setText(LoginActivity.usuario.getApellidos());
        nif.setText(LoginActivity.usuario.getNif());
        correo.setText(LoginActivity.usuario.getCorreo());
        contrasena.setText(LoginActivity.usuario.getContrasenya());
    /* telefono.setText();
        direccion.setText();
        localidad.setText();
        codigoPostal.setText();
        tarjeta.setText();
        fechaTarjeta.setText();*/
        //todo Futuro - cargar todos los datos
    }
}