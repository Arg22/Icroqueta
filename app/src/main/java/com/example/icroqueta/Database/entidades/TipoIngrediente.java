package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.TipoIngredienteTable;

@SuppressWarnings("unused")
public class TipoIngrediente {
    private Integer idIngredienteTipo;
    private int idIngrediente;
    private int idTipo;

    public TipoIngrediente() {
    }

    public TipoIngrediente(int idTipo, int idIngrediente) {
        this.idIngrediente = idIngrediente;
        this.idTipo = idTipo;
    }

    public TipoIngrediente(Integer idIngredienteTipo, int idIngrediente, int idTipo) {
        this.idIngredienteTipo = idIngredienteTipo;
        this.idIngrediente = idIngrediente;
        this.idTipo = idTipo;
    }

    public Integer getIdIngredienteTipo() {
        return idIngredienteTipo;
    }

    public void setIdIngredienteTipo(Integer idIngredienteTipo) {
        this.idIngredienteTipo = idIngredienteTipo;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getidTipo() {
        return idTipo;
    }

    public void setidTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * Mapear sirve para meter valores y crear un mapa
     * mete en cada columna de la tabla, el dato del objeto
     * (escribe la informacion de la tabla).
     *
     * @return values es el mapa de los IngredienteTipo.
     */
    public ContentValues mapearAContenValues() {
        ContentValues values = new ContentValues();
        values.put(TipoIngredienteTable.ID_TIPO_INGREDIENTE, idIngredienteTipo);
        values.put(TipoIngredienteTable.ID_INGREDIENTE, idIngrediente);
        values.put(TipoIngredienteTable.ID_TIPO, idTipo);
        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto IngredienteTipo.
     */
    public TipoIngrediente loadTipoIngredienteFromCursor(Cursor cursor) {

        int idIngredienteTipo = cursor.getInt(cursor.getColumnIndexOrThrow(TipoIngredienteTable.ID_TIPO_INGREDIENTE));
        int idIngrediente = cursor.getInt(cursor.getColumnIndexOrThrow(TipoIngredienteTable.ID_INGREDIENTE));
        int idTipo = cursor.getInt(cursor.getColumnIndexOrThrow(TipoIngredienteTable.ID_TIPO));

        this.idIngredienteTipo = idIngredienteTipo;
        this.idIngrediente = idIngrediente;
        this.idTipo = idTipo;

        return this;
    }
}
