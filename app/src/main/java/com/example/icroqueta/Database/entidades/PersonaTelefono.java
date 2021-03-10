package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.PersonaTelefonoTable;

@SuppressWarnings("unused")
public class PersonaTelefono implements java.io.Serializable {
    private Integer idPersonaTelefono;
    private int idPersona;
    private int idTelefono;

    public PersonaTelefono() {
    }

    public PersonaTelefono(int idPersona, int idTelefono) {
        this.idPersona = idPersona;
        this.idTelefono = idTelefono;
    }

    public PersonaTelefono(Integer idPersonaTelefono, int idPersona, int idTelefono) {
        this.idPersonaTelefono = idPersonaTelefono;
        this.idPersona = idPersona;
        this.idTelefono = idTelefono;
    }

    public Integer getIdPersonaTelefono() {
        return idPersonaTelefono;
    }

    public void setIdPersonaTelefono(Integer idPersonaTelefono) {
        this.idPersonaTelefono = idPersonaTelefono;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
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
        values.put(PersonaTelefonoTable.ID_PERSONA_TELEFONO, idPersonaTelefono);
        values.put(PersonaTelefonoTable.ID_PERSONA, idPersona);
        values.put(PersonaTelefonoTable.ID_TELEFONO, idTelefono);
        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto pedido.
     */
    public PersonaTelefono loadPersonaTelefonoFromCursor(Cursor cursor) {

        int idPersonaTelefono = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTelefonoTable.ID_PERSONA_TELEFONO));
        int idPersona = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTelefonoTable.ID_PERSONA));
        int idTelefono = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaTelefonoTable.ID_TELEFONO));

        this.idPersonaTelefono = idPersonaTelefono;
        this.idPersona = idPersona;
        this.idTelefono = idTelefono;

        return this;
    }

}


