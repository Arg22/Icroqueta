package com.example.icroqueta.database.entidades;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.DireccionTable;

public class Direccion implements java.io.Serializable {

    private Integer idDireccion;
    private String calle;
    private String localidad;
    private String codigoPostal;
    private String coordenada;

    public Direccion() {
    }

    public Direccion(String calle, String localidad, String codigoPostal, String coordenada) {
        this.calle = calle;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.coordenada = coordenada;
    }

    public Direccion(Integer idDireccion, String calle, String localidad, String codigoPostal, String coordenada) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.coordenada = coordenada;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
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
        values.put(DireccionTable.ID_DIRECCION, idDireccion);
        values.put(DireccionTable.CALLE, calle);
        values.put(DireccionTable.LOCALIDAD, localidad);
        values.put(DireccionTable.CODIGO_POSTAL, codigoPostal);
        values.put(DireccionTable.COORDENADA, coordenada);

        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto Direccion.
     */
    public Direccion loadDireccionFromCursor(Cursor cursor) {

        int idDireccion = cursor.getInt(cursor.getColumnIndexOrThrow(DireccionTable.ID_DIRECCION));
        String calle = cursor.getString(cursor.getColumnIndexOrThrow(DireccionTable.CALLE));
        String localidad = cursor.getString(cursor.getColumnIndexOrThrow(DireccionTable.LOCALIDAD));
        String codigoPostal = cursor.getString(cursor.getColumnIndexOrThrow(DireccionTable.CODIGO_POSTAL));
        String coordenada = cursor.getString(cursor.getColumnIndexOrThrow(DireccionTable.COORDENADA));

        this.idDireccion = idDireccion;
        this.calle = calle;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.coordenada = coordenada;

        return this;
    }
}


