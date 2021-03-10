package com.example.icroqueta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Direccion;
import com.example.icroqueta.database.entidades.Persona;
import com.example.icroqueta.utils.LocalizadorDirecciones;
import com.example.icroqueta.utils.ValidadorDNI;
import com.google.android.gms.maps.model.LatLng;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

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
    EditText portal;
    EditText puerta;
    EditText localidad;
    EditText codigoPostal;
    String nom, ape, n, cor, con, tel, dir, por, pu, cod, loc;
    int idPersona;

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
        boolean todoCorrecto = true;
        //Comprobamos que no ha dejado vacíos los campos obligatorios
        if (nombre.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "No puede dejar vacío el nombre", Toast.LENGTH_LONG).show();
        } else if (apellido.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "No puede dejar vacío el apellido", Toast.LENGTH_LONG).show();
        } else if (nif.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "No puede dejar vacío el Nif", Toast.LENGTH_LONG).show();
        } else if (correo.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "No puede dejar vacío el correo", Toast.LENGTH_LONG).show();
        } else if (contrasena.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "No puede dejar vacía la contraseña", Toast.LENGTH_LONG).show();
        } else {
            //Si se ha modificado el nombre se actualiza en el bd
            if (!nombre.getText().toString().matches(nom) ) {
                cambiosNombre();
                todoCorrecto = true;
            }
            //Si se ha modificado el apellido se actualiza en el bd
            if (!apellido.getText().toString().matches(ape)) {
                cambiosApellido();
                todoCorrecto = true;
            }
            //Si se ha modificado el nif primero hay que validarlo
            if (!nif.getText().toString().matches(n)) {
                if (!cambiosNif()) {
                    Toast.makeText(this, "Compruebe el Nif", Toast.LENGTH_LONG).show();
                    todoCorrecto = false;
                }
            }
            //Si se ha modificado el correo primero hay que validarlo
            if (!correo.getText().toString().matches(cor) && todoCorrecto) {
                if (!cambiosCorreo()) {
                    Toast.makeText(this, "El correo ya está registrado por otra cuenta", Toast.LENGTH_LONG).show();
                    todoCorrecto = false;
                }
            }
            //Si se ha modificado la contraseña primero hay que validarlo
            if (!contrasena.getText().toString().matches(con) && todoCorrecto) {
                cambiosContrasenya();
                todoCorrecto = true;
            }

            //Si se ha modificado el telefono primero hay que validarlo
            if (!telefono.getText().toString().matches(tel) && todoCorrecto) {
                if (!cambiosTelefono()) {
                    Toast.makeText(this, "Compruebe el teléfono", Toast.LENGTH_LONG).show();
                    todoCorrecto = false;
                }
            }

            //Si se ha modificado el telefono primero hay que validarlo
            if ((!direccion.getText().toString().matches(dir) || !localidad.getText().toString().matches(loc) || !codigoPostal.getText().toString().matches(cod) || !puerta.getText().toString().matches(pu) || !portal.getText().toString().matches(por)) && todoCorrecto) {
                if (!cambiosDireccion()) {
                    Toast.makeText(this, "Compruebe que los datos de la direccion estan todos rellenos o correctos", Toast.LENGTH_LONG).show();
                    todoCorrecto = false;
                }
            }
            //Si están los datos correctos, sale un aviso
            if (todoCorrecto) {
                Toast.makeText(this, "Cambios guardados con exito", Toast.LENGTH_LONG).show();
            }
        }
    }


    //todo share preferences ultimo telefono/ ultima direccion/ultima tarjeta


    /**
     * Método para modificar el dato del nombre
     */
    public void cambiosNombre() {
        DBHelper db = new DBHelper();
        db.updateNombrePersona(this, idPersona, nombre.getText().toString());
    }

    /**
     * Método para modificar el dato del apellido
     *
     */
    public void cambiosApellido() {
        DBHelper db = new DBHelper();
        db.updateApellidoPersona(this, idPersona, apellido.getText().toString());
    }

    /**
     * Método para modificar el dato del nif
     *
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosNif() {
        DBHelper db = new DBHelper();
        ValidadorDNI v = new ValidadorDNI(nif.getText().toString());
        if (v.validar()) {
            db.updateNifPersona(this, idPersona, nif.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para modificar el dato del correo
     *
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosCorreo() {
        DBHelper db = new DBHelper();
        //Comprobamos correo por si existe en la base de datos otro usuario con el mismo correo
        if (db.notExistCorreo(this, idPersona, correo.getText().toString())) {
            db.updateCorreoPersona(this, idPersona, correo.getText().toString());
            return true;
        } else {
            return false;
        }
    }


    /**
     * Método para modificar el dato de la contraseña
     *
     */
    public void cambiosContrasenya() {
        DBHelper db = new DBHelper();
        //Para encripar la contraseña
        String passEncriptada = new String(Hex.encodeHex(DigestUtils.sha1(contrasena.getText().toString())));
        db.updateContrasenyaPersona(this, idPersona, passEncriptada);
    }

    /**
     * Método para modificar el dato del telefono
     *
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosTelefono() {
        DBHelper db = new DBHelper();
        //Comprobamos el telefono
        if (!(telefono.getText().toString().length() < 9)) {
            //Se añade un telefono nuevo
            int aux = Integer.parseInt(telefono.getText().toString());
            db.addPersonaTelefono(this, idPersona, aux);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para modificar el dato de la direccion
     *
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosDireccion() {
        DBHelper db = new DBHelper();
        try {
            String dirCalle = direccion.getText().toString();
            String porCalle = portal.getText().toString();
            String pueCalle = puerta.getText().toString();
            String codCalle = codigoPostal.getText().toString();
            String locCalle = localidad.getText().toString();
            String coordenadas;
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
     * Método que borra la cuenta cuando presione dos veces y salga sin guardar
     */
    public void borrarCuenta() {
        long time = System.currentTimeMillis();

        if (time - backPressedTime > TIME_TO_CLOSE_APP) {
            backPressedTime = time;
            Toast.makeText(this, "Pulse otra vez para borrar cuenta", Toast.LENGTH_LONG).show();
        } else {
            DBHelper db = new DBHelper();
            db.deletePersona(this, idPersona);
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
        cargarIdUsuario();
        nombre = findViewById(R.id.nombreOpciones);
        apellido = findViewById(R.id.apellidoOpciones);
        nif = findViewById(R.id.nifOpciones);
        correo = findViewById(R.id.correoOpciones);
        contrasena = findViewById(R.id.contrasenaOpciones);
        telefono = findViewById(R.id.telefonoOpciones);
        direccion = findViewById(R.id.direccionOpciones);
        portal = findViewById(R.id.portalOpciones);
        puerta = findViewById(R.id.puertaOpciones);
        localidad = findViewById(R.id.localidadOpciones);
        codigoPostal = findViewById(R.id.cPostalOpciones);

        Persona p = db.findPersonaId(this, idPersona);

        nombre.setText(p.getNombre());
        apellido.setText(p.getApellidos());
        nif.setText(p.getNif());
        correo.setText(p.getCorreo());
        contrasena.setText(R.string.esto_es_por_seguridad);
        telefono.setText(db.oneTelefono(this, idPersona));

        Direccion d = db.oneDireccion(this, idPersona);
        if (d != null) {
            direccion.setText(d.getCalle());
            portal.setText(d.getPortal());
            puerta.setText(d.getPuerta());
            localidad.setText(d.getLocalidad());
            codigoPostal.setText(d.getCodigoPostal());
        }

        nom = nombre.getText().toString();
        ape = apellido.getText().toString();
        n = nif.getText().toString();
        cor = correo.getText().toString();
        con = contrasena.getText().toString();
        tel = telefono.getText().toString();
        dir = direccion.getText().toString();
        por = portal.getText().toString();
        pu = puerta.getText().toString();

        cod = codigoPostal.getText().toString();
        loc = localidad.getText().toString();
    }


    /**
     * Método para sacar el id del usuario de las credenciales guardadas
     */
    private void cargarIdUsuario() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        idPersona = preferences.getInt("id", 0);
    }

}