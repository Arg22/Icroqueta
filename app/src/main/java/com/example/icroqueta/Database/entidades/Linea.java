package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.LineaTable;

public class Linea  implements java.io.Serializable {
     private Integer idLinea;
     private int idPedido;
     private int idProducto;
     private int cantidad;

    public Linea() {
    }

    public Linea(int idPedido, int idProducto, int cantidad) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Linea(Integer idLinea, int idPedido, int idProducto, int cantidad) {
        this.idLinea = idLinea;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
        values.put(LineaTable.ID_LINEA, idLinea);
        values.put(LineaTable.ID_PEDIDO, idPedido);
        values.put(LineaTable.ID_PRODUCTO, idProducto);
        values.put(LineaTable.CANTIDAD, cantidad);

        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto Linea.
     */
    public Linea loadLineaFromCursor(Cursor cursor) {

        int idLinea = cursor.getInt(cursor.getColumnIndexOrThrow(LineaTable.ID_LINEA));
        int idPedido = cursor.getInt(cursor.getColumnIndexOrThrow(LineaTable.ID_PEDIDO));
        int idProducto = cursor.getInt(cursor.getColumnIndexOrThrow(LineaTable.ID_PRODUCTO));
        int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(LineaTable.CANTIDAD));

        this.idLinea = idLinea;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;

        return this;
    }

}


