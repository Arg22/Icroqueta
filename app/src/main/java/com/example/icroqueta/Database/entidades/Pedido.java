package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.PedidoTable;

@SuppressWarnings("unused")
public class Pedido implements java.io.Serializable {


    private Integer idPedido;
    private Integer idPersona;
    private String fechaPedido;
    private String estado;
    private String telefono;
    private String coordenadas;
    private double importe;

    public Pedido() {
    }

    public Pedido(Integer idPedido, Integer idPersona, String fechaPedido, String estado, String telefono, String coordenadas, double importe) {
        this.idPedido = idPedido;
        this.idPersona = idPersona;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.telefono = telefono;
        this.coordenadas = coordenadas;
        this.importe = importe;

    }

    public Pedido(Integer persona, String fechaPedido, String estado, String telefono, String coordenadas, double importe) {
        this.idPersona = persona;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.telefono = telefono;
        this.coordenadas = coordenadas;
        this.importe = importe;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPedido() {
        return this.idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
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
        values.put(PedidoTable.TELEFONO, telefono);
        values.put(PedidoTable.COORDENADAS, coordenadas);
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
        String telefono = cursor.getString(cursor.getColumnIndexOrThrow(PedidoTable.TELEFONO));
        String coordenadas = cursor.getString(cursor.getColumnIndexOrThrow(PedidoTable.COORDENADAS));
        double importe = cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoTable.IMPORTE));

        this.idPedido = idPedido;
        this.idPersona = idPersona;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.telefono = telefono;
        this.coordenadas = coordenadas;
        this.importe = importe;
        return this;
    }
}
