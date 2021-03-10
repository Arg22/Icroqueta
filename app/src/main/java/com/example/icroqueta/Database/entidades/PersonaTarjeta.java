package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.PersonaTarjetaTable;

@SuppressWarnings("unused")
public class PersonaTarjeta implements java.io.Serializable {

    private Integer idPersonaTarjeta;
    private int idPersona;
    private int idTarjeta;

    public PersonaTarjeta() {
    }

    public PersonaTarjeta(int idPersona, int idTarjeta) {
        this.idPersona = idPersona;
        this.idTarjeta = idTarjeta;
    }

    public PersonaTarjeta(Integer idPersonaTarjeta, int idPersona, int idTarjeta) {
        this.idPersonaTarjeta = idPersonaTarjeta;
        this.idPersona = idPersona;
        this.idTarjeta = idTarjeta;
    }

    public Integer getIdPersonaTarjeta() {
        return idPersonaTarjeta;
    }

    public void setIdPersonaTarjeta(Integer idPersonaTarjeta) {
        this.idPersonaTarjeta = idPersonaTarjeta;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    /**
     * Mapear sirve para meter valores y crear un mapa
     * mete en cada columna de la tabla, el dato del objeto
     * (escribe la informacion de la tabla).
     *
     * @return values es el mapa de los objetos
     */
    public ContentValues mapearAContenValues() {
        ContentValues values = new ContentValues();
        values.put(PersonaTarjetaTable.ID_PERSONA_TARJETA, idPersonaTarjeta);
        values.put(PersonaTarjetaTable.ID_PERSONA, idPersona);
        values.put(PersonaTarjetaTable.ID_TARJETA, idTarjeta);
        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto persona_tarjeta.
     */
    public PersonaTarjeta loadPersonaTarjetaFromCursor(Cursor cursor) {

        int idPersonaTarjeta = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTarjetaTable.ID_PERSONA_TARJETA));
        int idPersona = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTarjetaTable.ID_PERSONA));
        int idTarjeta = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTarjetaTable.ID_TARJETA));

        this.idPersonaTarjeta = idPersonaTarjeta;
        this.idPersona = idPersona;
        this.idTarjeta = idTarjeta;

        return this;
    }

}


