package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    String nom, ape, n, cor, con, tel, dir, loc, cod;


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
        DBHelper db = new DBHelper();
        int aux;
        if (nombre.getText().toString().matches("")) {
            Toast.makeText(this, "No puede dejar vacio el nombre", Toast.LENGTH_LONG).show();
        } else if (nombre.getText().toString().matches("")) {
            Toast.makeText(this, "No puede dejar vacio el nombre", Toast.LENGTH_LONG).show();
        } else if (nombre.getText().toString().matches("")) {
            Toast.makeText(this, "No puede dejar vacio el correo", Toast.LENGTH_LONG).show();
        } else if (nombre.getText().toString().matches("")) {
            Toast.makeText(this, "No puede dejar vacia la contraseña", Toast.LENGTH_LONG).show();
        } else if (nombre.getText().toString().matches(nom) && apellido.getText().toString().matches(ape) && nif.getText().toString().matches(n) && correo.getText().toString().matches(cor) && contrasena.getText().toString().matches(con) && telefono.getText().toString().matches(tel) && direccion.getText().toString().matches(dir) && localidad.getText().toString().matches(loc) && codigoPostal.getText().toString().matches(cod)) {
            Toast.makeText(this, "No ha realizado ningún cambio", Toast.LENGTH_LONG).show();
        } else {
            //todo comporbar usuario


            //Comprobamos user name por si existe en la base de datos otro usuario con el mismo nombre


            // Comprobamos nif
            if (!compruebaLetraDNI(nif.getText().toString())) {
                Toast.makeText(this, "Compruebe el dni", Toast.LENGTH_LONG).show();
            }
            //Comprobamos correo por si existe en la base de datos otro usuario con el mismo nombre


            //Comprobamos telefono
            if ((!telefono.getText().toString().matches(tel)) && tel.matches("")) {
                //Se añade un telefono nuevo
                aux = Integer.parseInt(telefono.getText().toString());
                db.addPersonaTelefono(this, LoginActivity.usuario.getIdPersona(), aux);
            }
            // db.updatePersona(this,nombre.getText(),apellido.getText(),nif.getText(),correo.getText(),contrasena.getText(),telefono.getText(),direccion.getText(),localidad.getText(),codigoPostal.getText();
            Toast.makeText(this, "Cambios guardados con exito", Toast.LENGTH_LONG).show();
        }


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

    /**
     * Método para cargar los datos disponibles del usuario
     */
    public void cargarDatos() {
        DBHelper db = new DBHelper();
        nombre = findViewById(R.id.nombreOpciones);
        apellido = findViewById(R.id.apellidoOpciones);
        nif = findViewById(R.id.nifOpciones);
        correo = findViewById(R.id.correoOpciones);
        contrasena = findViewById(R.id.contrasenaOpciones);
        telefono = findViewById(R.id.telefonoOpciones);
        direccion = findViewById(R.id.direccionOpciones);
        localidad = findViewById(R.id.localidadOpciones);
        codigoPostal = findViewById(R.id.cPostalOpciones);


        nombre.setText(LoginActivity.usuario.getNombre());
        apellido.setText(LoginActivity.usuario.getApellidos());
        nif.setText(LoginActivity.usuario.getNif());
        correo.setText(LoginActivity.usuario.getCorreo());
        contrasena.setText(LoginActivity.usuario.getContrasenya());
        telefono.setText(db.oneTelefono(this, LoginActivity.usuario.getIdPersona()));

      /*direccion.setText();
        localidad.setText();
        codigoPostal.setText();*/
        //todo - cargar todos los datos
        //todo-guardar coordenadas https://es.stackoverflow.com/questions/196676/extraer-coordenadas-de-una-direccion-con-la-api-de-google-maps
        //https://developers.google.com/maps/documentation/javascript/markers
        // https://developers.google.com/maps/documentation/geocoding/overview

        nom = nombre.getText().toString();
        ape = apellido.getText().toString();
        n = nif.getText().toString();
        cor = correo.getText().toString();
        con = contrasena.getText().toString();
        tel = telefono.getText().toString();
        dir = direccion.getText().toString();
        loc = localidad.getText().toString();
        cod = codigoPostal.getText().toString();
    }

    private boolean compruebaLetraDNI(String dni) {
        try {
            int num = Integer.parseInt(dni.substring(0, 8));
            char letra = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(num % 23);
            if (dni.charAt(8) != letra)
                return false;
        } catch (Exception e) {
            Log.i("ValidacionDNI", e.getClass().toString());
            return false;
        }
        return true;
    }

}