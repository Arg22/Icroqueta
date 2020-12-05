package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.PedidoTable;

public class Pedido  implements java.io.Serializable {


     private Integer idPedido;
     private Integer idPersona;
     private String fechaPedido;
     private String estado;
     private double importe;

    public Pedido() {
    }

    public Pedido(Integer idPedido, Integer idPersona, String fechaPedido, String estado, double importe) {
        this.idPedido = idPedido;
        this.idPersona = idPersona;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.importe = importe;
    }

    public Pedido(Integer persona, String fechaPedido, String estado, double importe) {
        this.idPersona = persona;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.importe = importe;
    }


   
    public Integer getIdPedido() {
        return this.idPedido;
    }
    
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    public Integer getPersona() {
        return this.idPersona;
    }
    
    public void setPersona(Integer persona) {
        this.idPersona = idPersona;
    }
    public String getFechaPedido() {
        return this.fechaPedido;
    }
    
    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public double getImporte() {
        return this.importe;
    }
    
    public void setImporte(double importe) {
        this.importe = importe;
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
        values.put(PedidoTable.ID_PEDIDO, idPedido);
        values.put(PedidoTable.ID_PERSONA, idPersona);
        values.put(PedidoTable.FECHA_PEDIDO, String.valueOf(fechaPedido));
        values.put(PedidoTable.ESTADO, estado);
        values.put(PedidoTable.IMPORTE, importe);
        return values;
    }

    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto pedido.
     */
    public Pedido loadPedidoaFromCursor(Cursor cursor) {

        int idPedido = cursor.getInt(cursor.getColumnIndexOrThrow(PedidoTable.ID_PEDIDO));
        int idPersona = cursor.getInt(cursor.getColumnIndexOrThrow(PedidoTable.ID_PERSONA));
        String fechaPedido = cursor.getString(cursor.getColumnIndexOrThrow(PedidoTable.FECHA_PEDIDO));
        String estado = cursor.getString(cursor.getColumnIndexOrThrow(PedidoTable.ESTADO));
        Double importe = cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoTable.IMPORTE));

        this.idPedido = idPedido;
        this.idPersona = idPersona;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.importe = importe;

        return this;
    }
}
