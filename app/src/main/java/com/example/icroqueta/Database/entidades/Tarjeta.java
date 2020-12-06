package com.example.icroqueta.database.entidades;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.TarjetaTable;

public class Tarjeta  implements java.io.Serializable {


     private Integer idTarjeta;
     private String numero;
     private String fechaCaducidad;

    public Tarjeta() {
    }

    public Tarjeta(Integer idTarjeta, String numero, String fechaCaducidad) {
        this.idTarjeta = idTarjeta;
        this.numero = numero;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Tarjeta(String numero, String fechaCaducidad) {
        this.numero = numero;
        this.fechaCaducidad = fechaCaducidad;
    }

   
    public Integer getIdTarjeta() {
        return this.idTarjeta;
    }
    
    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getFechaCaducidad() {
        return this.fechaCaducidad;
    }
    
    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
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
        values.put(TarjetaTable.ID_TARJETA, idTarjeta);
        values.put(TarjetaTable.FECHA_CADUCIDAD, String.valueOf(fechaCaducidad));
        values.put(TarjetaTable.NUMERO, numero);
        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto tarjeta.
     */
    public Tarjeta loadPedidoaFromCursor(Cursor cursor) {

        int idTarjeta = cursor.getInt(cursor.getColumnIndexOrThrow(TarjetaTable.ID_TARJETA));
        String fechaCaducidad = cursor.getString(cursor.getColumnIndexOrThrow(TarjetaTable.FECHA_CADUCIDAD));
        String numero = cursor.getString(cursor.getColumnIndexOrThrow(TarjetaTable.NUMERO));
        this.idTarjeta = idTarjeta;
        this.numero = numero;
        this.fechaCaducidad = fechaCaducidad;
        return this;
    }

}


