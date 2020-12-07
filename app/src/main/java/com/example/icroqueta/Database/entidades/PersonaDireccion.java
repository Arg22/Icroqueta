package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.PersonaDireccionTable;

public class PersonaDireccion  implements java.io.Serializable {

    private Integer idPersonaDireccion;
    private int idPersona;
    private int idDireccion;

    public PersonaDireccion() {
    }

    public PersonaDireccion(int idPersona, int idDireccion) {
        this.idPersona = idPersona;
        this.idDireccion = idDireccion;
    }

    public PersonaDireccion(Integer idPersonaDireccion, int idPersona, int idDireccion) {
        this.idPersonaDireccion = idPersonaDireccion;
        this.idPersona = idPersona;
        this.idDireccion = idDireccion;
    }

    public Integer getIdPersonaDireccion() {
        return idPersonaDireccion;
    }

    public void setIdPersonaDireccion(Integer idPersonaDireccion) {
        this.idPersonaDireccion = idPersonaDireccion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
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
        values.put(PersonaDireccionTable.ID_PERSONA_Direccion, idPersonaDireccion);
        values.put(PersonaDireccionTable.ID_PERSONA, idPersona);
        values.put(PersonaDireccionTable.ID_DIRECCION, idDireccion);
        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto persona_Direccion.
     */
    public PersonaDireccion loadPersonaDireccionFromCursor(Cursor cursor) {

        int idPersonaDireccion = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaDireccionTable.ID_PERSONA_Direccion));
        int idPersona = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaDireccionTable.ID_PERSONA));
        int idDireccion = cursor.getInt(cursor.getColumnIndexOrThrow(PersonaDireccionTable.ID_DIRECCION));

        this.idPersonaDireccion = idPersonaDireccion;
        this.idPersona = idPersona;
        this.idDireccion = idDireccion;

        return this;
    }
}


