package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.CarritoTable;

public class Carrito  implements java.io.Serializable {

    private Integer idCarrito;
    private int idPedido;
    private int idProducto;
    private int cantidad;

    public Carrito() {
    }

    public Carrito(int idPedido, int idProducto, int cantidad) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Carrito(Integer idCarrito, int idPedido, int idProducto, int cantidad) {
        this.idCarrito = idCarrito;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
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
        values.put(CarritoTable.ID_CARRITO, idCarrito);
        values.put(CarritoTable.ID_PEDIDO, idPedido);
        values.put(CarritoTable.ID_PRODUCTO, idProducto);
        values.put(CarritoTable.CANTIDAD, cantidad);


        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto Carrito.
     */
    public Carrito loadPedidoaFromCursor(Cursor cursor) {

        int idCarrito = cursor.getInt(cursor.getColumnIndexOrThrow(CarritoTable.ID_CARRITO));
        int idPedido = cursor.getInt(cursor.getColumnIndexOrThrow(CarritoTable.ID_PEDIDO));
        int idProducto = cursor.getInt(cursor.getColumnIndexOrThrow(CarritoTable.ID_PRODUCTO));
        int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(CarritoTable.CANTIDAD));

        this.idCarrito = idCarrito;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;

        return this;
    }

}