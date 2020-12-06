package com.example.icroqueta.database.DTO;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import com.example.icroqueta.database.entidades.Producto;
import com.example.icroqueta.database.tablas.CarritoTable;
import com.example.icroqueta.database.tablas.ProductoTable;

public class ProductoCarrito extends Producto {
    private int cantidad;
    private int idPersona;

    public ProductoCarrito() {
    }


    public int getCantidad() {
        return cantidad;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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
        if (cursor.getColumnIndex(CarritoTable.CANTIDAD) != -1) {
            this.cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(CarritoTable.CANTIDAD));
        }
        if (cursor.getColumnIndex(CarritoTable.ID_PERSONA) != -1) {
            this.idPersona = cursor.getInt(cursor.getColumnIndexOrThrow(CarritoTable.ID_PERSONA));
        }
        setIdProducto(idProducto);

        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecioUd(precioUd);
        setStock(stock);
        setDescuento(descuento);
        setImagen(imagen);




        return this;
    }

}
