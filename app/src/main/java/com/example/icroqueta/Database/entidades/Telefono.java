package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.TelefonoTable;



public class Telefono implements java.io.Serializable {


    private Integer idTelefono;
    private int numero;

    public Telefono(Integer idTelefono, int numero) {
        this.idTelefono = idTelefono;
        this.numero = numero;
    }
    public Telefono() {
    }

    public Telefono(int numero) {
        this.numero = numero;
    }

    public Integer getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

        values.put(TelefonoTable.NUMERO, numero);
        values.put(TelefonoTable.ID_TELEFONO, idTelefono);

        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto telefono.
     */
    public Telefono loadTelefonoFromCursor(Cursor cursor) {
        int idTelefono = cursor.getInt(cursor.getColumnIndexOrThrow(TelefonoTable.ID_TELEFONO));
        int numero = cursor.getInt(cursor.getColumnIndexOrThrow(TelefonoTable.NUMERO));
        this.idTelefono = idTelefono;
        this.numero = numero;
        return this;
    }

}


