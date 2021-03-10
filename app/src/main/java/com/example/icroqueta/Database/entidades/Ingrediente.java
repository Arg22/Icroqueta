package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.IngredienteTable;

@SuppressWarnings("unused")
public class Ingrediente implements java.io.Serializable {


    private Integer idIngrediente;
    private String nombre;

    public Ingrediente() {
    }

    public Ingrediente(Integer idIngrediente, String nombre) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
    }

    public Ingrediente(String nombre) {
        this.nombre = nombre;

    }

    public Integer getIdIngrediente() {
        return this.idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
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
        values.put(IngredienteTable.ID_INGREDIENTE, idIngrediente);
        values.put(IngredienteTable.NOMBRE, nombre);
        return values;
    }


    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto ingrediente.
     */
    public Ingrediente loadIngredienteFromCursor(Cursor cursor) {
        int idIngrediente = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteTable.ID_INGREDIENTE));
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(IngredienteTable.NOMBRE));
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;

        return this;
    }
}