package com.example.icroqueta.database.DTO;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.entidades.Producto;
import com.example.icroqueta.database.tablas.CarritoTable;
import com.example.icroqueta.database.tablas.ProductoTable;

public class ProductoCarrito extends Producto {
    private int cantidad;

    public ProductoCarrito() {
    }

    public ProductoCarrito(Integer idProducto, String nombre, String descripcion, double precioUd, int stock, double descuento, String imagen, int cantidad) {
        super(idProducto, nombre, descripcion, precioUd, stock, descuento, imagen);
        this.cantidad = cantidad;
    }

    public ProductoCarrito(String nombre, String descripcion, double precioUd, int stock, double descuento, String imagen, int cantidad) {
        super(nombre, descripcion, precioUd, stock, descuento, imagen);

        this.cantidad = cantidad;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto producto.
     */
    public ProductoCarrito loadProductoCarritoFromCursor(Cursor cursor) {
        int idProducto = cursor.getInt(cursor.getColumnIndexOrThrow(ProductoTable.ID_PRODUCTO));
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(ProductoTable.NOMBRE));
        String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(ProductoTable.DESCRIPCION));
        double precioUd = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductoTable.PRECIO_UD));
        int stock = cursor.getInt(cursor.getColumnIndexOrThrow(ProductoTable.STOCK));
        double descuento = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductoTable.DESCUENTO));
        String imagen = cursor.getString(cursor.getColumnIndexOrThrow(ProductoTable.IMAGEN));
        int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(CarritoTable.CANTIDAD));

        setIdProducto(idProducto);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecioUd(precioUd);
        setStock(stock);
        setDescuento(descuento);
        setImagen(imagen);

        this.cantidad = cantidad;

        return this;
    }

}
