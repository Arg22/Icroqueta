package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.TipoTable;

@SuppressWarnings("unused")
public class Tipo implements java.io.Serializable {


    private Integer idTipo;
    private String nombre;

    public Tipo() {
    }

    public Tipo(Integer idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public Tipo(String nombre) {
        this.nombre = nombre;

    }

    public Integer getIdTipo() {
        return this.idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        values.put(TipoTable.ID_TIPO, idTipo);
        values.put(TipoTable.NOMBRE, nombre);
        return values;
    }


    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto Tipo.
     */
    public Tipo loadTipoFromCursor(Cursor cursor) {
        int idTipo = cursor.getInt(cursor.getColumnIndexOrThrow(TipoTable.ID_TIPO));
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(TipoTable.NOMBRE));
        this.idTipo = idTipo;
        this.nombre = nombre;
        return this;
    }
}