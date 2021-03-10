package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.IngredienteProductoTable;

@SuppressWarnings("unused")
public class IngredienteProducto {
    private Integer idIngredienteProducto;
    private int idIngrediente;
    private int idProducto;

    public IngredienteProducto() {
    }

    public IngredienteProducto(int idProducto, int idIngrediente) {
        this.idIngrediente = idIngrediente;
        this.idProducto = idProducto;
    }

    public IngredienteProducto(Integer idIngredienteProducto, int idIngrediente, int idProducto) {
        this.idIngredienteProducto = idIngredienteProducto;
        this.idIngrediente = idIngrediente;
        this.idProducto = idProducto;
    }

    public Integer getIdIngredienteProducto() {
        return idIngredienteProducto;
    }

    public void setIdIngredienteProducto(Integer idIngredienteProducto) {
        this.idIngredienteProducto = idIngredienteProducto;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Mapear sirve para meter valores y crear un mapa
     * mete en cada columna de la tabla, el dato del objeto
     * (escribe la informacion de la tabla).
     *
     * @return values es el mapa de los IngredienteProducto.
     */
    public ContentValues mapearAContenValues() {
        ContentValues values = new ContentValues();
        values.put(IngredienteProductoTable.ID_INGREDIENTE_PRODUCTO, idIngredienteProducto);
        values.put(IngredienteProductoTable.ID_INGREDIENTE, idIngrediente);
        values.put(IngredienteProductoTable.ID_PRODUCTO, idProducto);
        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto IngredienteProducto.
     */
    public IngredienteProducto loadIngredienteProductoFromCursor(Cursor cursor) {

        int idIngredienteProducto = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteProductoTable.ID_INGREDIENTE_PRODUCTO));
        int idIngrediente = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteProductoTable.ID_INGREDIENTE));
        int idProducto = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteProductoTable.ID_PRODUCTO));

        this.idIngredienteProducto = idIngredienteProducto;
        this.idIngrediente = idIngrediente;
        this.idProducto = idProducto;

        return this;
    }
}
