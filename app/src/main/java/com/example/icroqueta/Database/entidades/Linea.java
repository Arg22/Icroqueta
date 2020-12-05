package com.example.icroqueta.database.entidades;
public class Linea  implements java.io.Serializable {


     private Integer idLinea;
     private Pedido pedido;
     private Producto producto;
     private int cantidad;

    public Linea() {
    }

    public Linea(Pedido pedido, Producto producto, int cantidad) {
       this.pedido = pedido;
       this.producto = producto;
       this.cantidad = cantidad;
    }
   
    public Integer getIdLinea() {
        return this.idLinea;
    }
    
    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}


