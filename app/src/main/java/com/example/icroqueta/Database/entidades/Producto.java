package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.ProductoTable;

@SuppressWarnings("unused")
public class Producto implements java.io.Serializable {

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private double precioUd;
    private int stock;
    private double descuento;
    private String imagen;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, double precioUd, int stock, double descuento, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUd = precioUd;
        this.stock = stock;
        this.descuento = descuento;
        this.imagen = imagen;
    }

    public Producto(Integer idProducto, String nombre, String descripcion, double precioUd, int stock, double descuento, String imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUd = precioUd;
        this.stock = stock;
        this.descuento = descuento;
        this.imagen = imagen;
    }

    public Integer getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUd() {
        return this.precioUd;
    }

    public void setPrecioUd(double precioUd) {
        this.precioUd = precioUd;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getDescuento() {
        return this.descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
        values.put(ProductoTable.ID_PRODUCTO, idProducto);
        values.put(ProductoTable.NOMBRE, nombre);
        values.put(ProductoTable.DESCRIPCION, descripcion);
        values.put(ProductoTable.PRECIO_UD, precioUd);
        values.put(ProductoTable.STOCK, stock);
        values.put(ProductoTable.DESCUENTO, descuento);
        values.put(ProductoTable.IMAGEN, imagen);

        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto producto.
     */
    public Producto loadProductoFromCursor(Cursor cursor) {
        int idProducto = cursor.getInt(cursor.getColumnIndexOrThrow(ProductoTable.ID_PRODUCTO));
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(ProductoTable.NOMBRE));
        String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(ProductoTable.DESCRIPCION));
        double precioUd = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductoTable.PRECIO_UD));
        int stock = cursor.getInt(cursor.getColumnIndexOrThrow(ProductoTable.STOCK));
        double descuento = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductoTable.DESCUENTO));
        String imagen = cursor.getString(cursor.getColumnIndexOrThrow(ProductoTable.IMAGEN));

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUd = precioUd;
        this.stock = stock;
        this.descuento = descuento;
        this.imagen = imagen;

        return this;
    }

}


