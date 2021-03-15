package com.example.icroqueta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Direccion;
import com.example.icroqueta.database.entidades.Tarjeta;
import com.example.icroqueta.database.entidades.Telefono;
import com.example.icroqueta.utils.LocalizadorDirecciones;
import com.google.android.gms.maps.model.LatLng;

import java.util.Objects;

public class RegisterPaymentActivity extends MenuBar {
    Intent intent;
    int idPersona;
    int idTelefono;
    int idDireccion;
    int idTarjeta;

    EditText telefono;
    EditText direccion;
    EditText portal;
    EditText puerta;
    EditText localidad;
    EditText codigoPostal;
    EditText tarjeta;
    EditText fecha;
    EditText cvc;
    CheckBox checkRecordad;
    String coordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_payment);
        cargarDatosPreferencias();
        cargarDatos();

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

            /**
             * Esto permite poner automáticamente / cuando añade el dia y la fecha de la tarjeta
             * @param s el texto que se va introduciendo por teclado
             */
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

    public void comprobarDatos(View view) {
        //comprueba que no haya campos vacios
        boolean todoCorrecto = true;
        if (portal.getText().toString().isEmpty() ||puerta.getText().toString().isEmpty() || codigoPostal.getText().toString().isEmpty() || cvc.getText().toString().isEmpty() || telefono.getText().toString().isEmpty() || direccion.getText().toString().isEmpty() || tarjeta.getText().toString().isEmpty() || fecha.getText().toString().isEmpty() || cvc.getText().toString().isEmpty() || localidad.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Rellene los campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            //Guardamos los datos si fuese necesario
            //Sacamos las coordenadas
            if (!sacarCoordenadas()) {
                Toast.makeText(this, "Compruebe que los datos de la direccion estan todos rellenos o correctos", Toast.LENGTH_LONG).show();
                todoCorrecto = false;
            } else {
                //En el caso de que esté el botón de recordar pulsado, actualizamos los datos del usuario
                if (checkRecordad.isChecked()) {
                    if (!cambiosTelefono()) {
                        Toast.makeText(this, "Compruebe el telefono", Toast.LENGTH_LONG).show();
                        todoCorrecto = false;
                    }
                    cambiosDireccion();
                    if (!cambiosTarjeta()) {
                        Toast.makeText(this, "Compruebe los datos de la tarjeta", Toast.LENGTH_LONG).show();
                        todoCorrecto = false;
                    }
                }
            }
            //validamos el pago si los datos están correctos
            if (todoCorrecto) {
                validarPago();
            }
        }
    }

    public void validarPago() {
        //Se elimina el carro una vez hecho y se crea las lineas del producto asociado a un pedido
        DBHelper db = new DBHelper();
        if (db.addPedido(this, idPersona, telefono.getText().toString(),coordenadas, puerta.getText().toString())) {
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
     * Método para sacar datos guardados
     */
    private void cargarDatosPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        boolean recordar = preferences.getBoolean("recordar_pago", true);
        if (recordar) {
            idPersona = preferences.getInt("id", 0);
            idTelefono = preferences.getInt("idTelefono"+idPersona, 0);
            idDireccion = preferences.getInt("idDireccion"+idPersona, 0);
            idTarjeta = preferences.getInt("idTarjeta"+idPersona, 0);
            checkRecordad = findViewById(R.id.RecordarDatosPago);
            checkRecordad.setChecked(true);
        }
    }


    /**
     * Método para modificar el dato del telefono
     *
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosTelefono() {
        DBHelper db = new DBHelper();
        //Comprobamos el telefono
        String auxNumTelefono = (telefono.getText().toString().trim());
        if (!(auxNumTelefono.length() < 9)) {
            //Se añade un telefono nuevo
            db.addPersonaTelefono(this, idPersona, Integer.parseInt(auxNumTelefono));
            Telefono idT = db.oneTelefonoByNum(this, Integer.parseInt(auxNumTelefono));
            SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("idTelefono"+idPersona, idT.getIdTelefono());
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para modificar el dato de la direccion
     *
     */
    public void cambiosDireccion() {
        DBHelper db = new DBHelper();
        try {
            String dirCalle = direccion.getText().toString();
            String porCalle = portal.getText().toString();
            String pueCalle = puerta.getText().toString();
            String codCalle = codigoPostal.getText().toString();
            String locCalle = localidad.getText().toString();
            db.addPersonaDireccion(this, idPersona, dirCalle, porCalle, pueCalle, codCalle, locCalle, coordenadas);
            Direccion idD = db.oneDireccionByCoord(this, coordenadas);
            SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("idDireccion" + idPersona, idD.getIdDireccion());
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para modificar el dato del telefono
     *
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosTarjeta() {
        DBHelper db = new DBHelper();
        //Comprobamos la tarjeta
        String numTarjeta = (tarjeta.getText().toString().trim());
        String f = fecha.getText().toString();
        String[] compruebaFicha = f.split("/");
        if (Integer.parseInt(compruebaFicha[0]) > 12 || Integer.parseInt(compruebaFicha[1]) < 20) {
            return false;
        }

        if (!(numTarjeta.length() < 16)) {
            //Se añade una tarjeta nuevo
            db.addPersonaTarjeta(this, idPersona, numTarjeta, f);
            Tarjeta trj = db.oneTarjetaByNum(this, numTarjeta);
            SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("idTarjeta"+idPersona, trj.getIdTarjeta());
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para sacar Las coordenadas de la direciccion
     */
    public boolean sacarCoordenadas() {
        try {
            DBHelper db = new DBHelper();
            String dirCalle = direccion.getText().toString();
            String porCalle = portal.getText().toString();
            String pueCalle = puerta.getText().toString();
            String codCalle = codigoPostal.getText().toString();
            String locCalle = localidad.getText().toString();
            String address = dirCalle + ", " + porCalle + ", " + codCalle + " " + locCalle;
            LocalizadorDirecciones ld = new LocalizadorDirecciones();
            LatLng latlon = ld.getLocationFromAddress(this, address);
            coordenadas = latlon.latitude + " " + latlon.longitude;
            db.addPersonaDireccion(this, idPersona, dirCalle, porCalle, pueCalle, codCalle, locCalle, coordenadas);
            return true;
        } catch (Exception e) {
            //En el caso de que la api no pueda leer la dirección saltará este error
            return false;
        }
    }


    /**
     * Método para cargar los datos disponibles del usuario
     */
    public void cargarDatos() {
        DBHelper db = new DBHelper();

        telefono = findViewById(R.id.telefonoPago);
        direccion = findViewById(R.id.direccionPago);
        localidad = findViewById(R.id.localidadPago);
        portal = findViewById(R.id.portalPago);
        puerta = findViewById(R.id.puertaPago);
        codigoPostal = findViewById(R.id.cPostalPago);
        tarjeta = findViewById(R.id.tarjetaPago);
        fecha = findViewById(R.id.fechaTarjetaPago);
        cvc = findViewById(R.id.cvcPago);

        Telefono t = db.oneTelefonoById(this, idTelefono);
        if (t != null) {
            telefono.setText(String.valueOf(t.getNumero()));
        }

        Direccion d = db.oneDireccionById(this, idDireccion);
        if (d != null) {
            direccion.setText(d.getCalle());
            portal.setText(d.getPortal());
            puerta.setText(d.getPuerta());
            localidad.setText(d.getLocalidad());
            codigoPostal.setText(d.getCodigoPostal());
            coordenadas = d.getCoordenada();
        }

        Tarjeta tj = db.oneTarjetaById(this, idTarjeta);
        if (tj != null) {
            tarjeta.setText(tj.getNumero());
            fecha.setText(tj.getFechaCaducidad());
        }
    }
}
