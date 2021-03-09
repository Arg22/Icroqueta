package com.example.icroqueta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Direccion;
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
    String nom, ape, n, cor, con, tel, dir,por,pu, cod,loc ;
int idPersona=LoginActivity.usuario.getIdPersona();

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
        //Comprobamos que no ha dejado vacios los campos obligatorios
        if (nombre.getText().toString().trim().matches("")) {
            Toast.makeText(this, "No puede dejar vacio el nombre", Toast.LENGTH_LONG).show();
        } else if (apellido.getText().toString().trim().matches("")) {
            Toast.makeText(this, "No puede dejar vacio el apellido", Toast.LENGTH_LONG).show();
        } else if (correo.getText().toString().trim().matches("")) {
            Toast.makeText(this, "No puede dejar vacio el correo", Toast.LENGTH_LONG).show();
        } else if (contrasena.getText().toString().trim().matches("")) {
            Toast.makeText(this, "No puede dejar vacia la contraseña", Toast.LENGTH_LONG).show();
        } else if (nombre.getText().toString().matches(nom) && apellido.getText().toString().matches(ape) && nif.getText().toString().matches(n) && correo.getText().toString().matches(cor) && contrasena.getText().toString().matches(con) && telefono.getText().toString().matches(tel) && direccion.getText().toString().matches(dir) && localidad.getText().toString().matches(loc) && codigoPostal.getText().toString().matches(cod) && puerta.getText().toString().matches(pu) && portal.getText().toString().matches(por)) {
            //Esto es por si le dan al boton de guardar sin haber modificado nada
            Toast.makeText(this, "No ha realizado ningún cambio", Toast.LENGTH_LONG).show();
        } else {
     

                    //Comprobamos el telefono
                    if (!(telefono.getText().toString().trim().matches("") || telefono.getText().toString().trim().matches(tel))) {
                        //Se añade un telefono nuevo
                        aux = Integer.parseInt(telefono.getText().toString());
                        db.addPersonaTelefono(this, idPersona, aux);
                    }
                    if (!(direccion.getText().toString().trim().matches("") || direccion.getText().toString().trim().matches(tel))) {
                        //Se añade una direccion nueva y primero leemos las coordenadas
                        String address;
                        if (contrasena.getText().toString().trim().matches("")) {
                            Toast.makeText(this, "Rellene los demás datos de la dirección", Toast.LENGTH_LONG).show();
                        } else {

                            address = direccion.getText().toString();


                            LocalizadorDirecciones ld = new LocalizadorDirecciones();
                            LatLng latlon = ld.getLocationFromAddress(this, address);
                            try {

                                String.valueOf(latlon.latitude);


                            } catch (Exception e) {
                                //En el caso de que la api no pueda leer la dirección saltará este error
                                Toast.makeText(this, "Compruebe la dirección", Toast.LENGTH_LONG).show();
                            }

                        }


                    }
                }
            }


            //todo añadir otro telefono al usuario

//todo direccion
            //si hay direccion, añadir localidad y poblacion y codigo postal

            //todo share preferences

            // db.updatePersona(this,nombre.getText(),apellido.getText(),nif.getText(),correo.getText(),contrasena.getText(),telefono.getText(),direccion.getText(),localidad.getText(),codigoPostal.getText();
            //     Toast.makeText(this, "Cambios guardados con exito", Toast.LENGTH_LONG).show();


    /**
     * Método para modificar el dato del nombre
     * @return true si ha sido modificado con éxito
     */
    public boolean cambiosNombre(){
        DBHelper db = new DBHelper();
        db.updateNombrePersona(this,idPersona,nombre.getText().toString());
        return true;
    }

    /**
     * Método para modificar el dato del apellido
     * @return true si ha sido modificado con éxito
     */
    public boolean cambiosApellido(){
        DBHelper db = new DBHelper();
        db.updateApellidoPersona(this,idPersona,apellido.getText().toString());
        return true;
    }

    /**
     * Método para modificar el dato del nif
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosNif() {
        DBHelper db = new DBHelper();
        ValidadorDNI v = new ValidadorDNI(nif.getText().toString());
        if (v.validar()) {
            db.updateNifPersona(this, idPersona, nif.getText().toString());
            return true;
        } else {
            Toast.makeText(this, "Compruebe el Nif", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * Método para modificar el dato del correo
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosCorreo() {
        DBHelper db = new DBHelper();
        //Comprobamos correo por si existe en la base de datos otro usuario con el mismo correo
        if (db.notExistCorreo(this, idPersona, correo.getText().toString())) {
            db.updateCorreoPersona(this, idPersona, correo.getText().toString());
            return true;
        } else {
              Toast.makeText(this, "El correo ya está registrado por otra cuenta", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    /**
     * Método para modificar el dato de la contraseña
     * @return true si ha sido modificado con éxito, false si no se ha podido modificar
     */
    public boolean cambiosContrasenya() {
        DBHelper db = new DBHelper();
        //Para encripar la contraseña
        String passEncriptada = new String(Hex.encodeHex(DigestUtils.sha1(contrasena.getText().toString())));
        db.updateContrasenyaPersona(this,idPersona,passEncriptada);
        return true;
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


        nombre.setText(LoginActivity.usuario.getNombre());
        apellido.setText(LoginActivity.usuario.getApellidos());
        nif.setText(LoginActivity.usuario.getNif());
        correo.setText(LoginActivity.usuario.getCorreo());
        contrasena.setText("*******");
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
        por  =portal.getText().toString();
        pu = puerta.getText().toString();

        cod = codigoPostal.getText().toString();
        loc = localidad.getText().toString();
    }


}