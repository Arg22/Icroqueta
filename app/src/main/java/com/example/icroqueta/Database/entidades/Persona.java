package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.PersonaTable;

public class Persona implements java.io.Serializable {


    private Integer idPersona;
    private String nif;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenya;
    private int rol;


    public Persona() {
    }

    public Persona(Integer idPersona, String nif, String nombre, String apellidos, String correo, String contrasenya, int rol) {
        this.idPersona = idPersona;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenya = contrasenya;
        this.rol = rol;
    }

    public Persona(String nif, String nombre, String apellidos, String correo, String contrasenya, int rol) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenya = contrasenya;
        this.rol = rol;
    }


    public Integer getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenya() {
        return this.contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public int isRol() {
        return this.rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    /**
     * Mapear sirve para meter valores y crear un mapa
     * mete en cada columna de la tabla, el dato del objeto
     * (escribe la informacion de la tabla).
     *
     * @return values es el mapa de los productos.
     */
    public ContentValues mapearAContenValues() {
        ContentValues values = new ContentValues();
        values.put(PersonaTable.ID_PERSONA, idPersona);
        values.put(PersonaTable.NIF, nif);
        values.put(PersonaTable.NOMBRE, nombre);
        values.put(PersonaTable.APELLIDOS, apellidos);
        values.put(PersonaTable.CORREO, correo);
        values.put(PersonaTable.CONTRASENA, contrasenya);
        values.put(PersonaTable.ROL, rol);

        return values;
    }


    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto persona.
     */
    public Persona loadProductoFromCursor(Cursor cursor) {
        int idPersona = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTable.ID_PERSONA));
        String nif = cursor.getString(cursor.getColumnIndexOrThrow(PersonaTable.NIF));
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(PersonaTable.NOMBRE));
        String apellidos = cursor.getString(cursor.getColumnIndexOrThrow(PersonaTable.APELLIDOS));
        String correo = cursor.getString(cursor.getColumnIndexOrThrow(PersonaTable.CORREO));
        String contrasenya = cursor.getString(cursor.getColumnIndexOrThrow(PersonaTable.CONTRASENA));
        int rol = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTable.ROL));

        this.idPersona = idPersona;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenya = contrasenya;
        this.rol = rol;

        return this;
    }
}


