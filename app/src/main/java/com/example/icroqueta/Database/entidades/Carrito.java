package com.example.icroqueta.database.entidades;

public class Carrito  implements java.io.Serializable {


     private Integer idCarrito;
     private Persona persona;
     private Producto producto;
     private int cantidad;

    public Carrito() {
    }

    public Carrito(Persona persona, Producto producto, int cantidad) {
       this.persona = persona;
       this.producto = producto;
       this.cantidad = cantidad;
    }
   
    public Integer getIdCarrito() {
        return this.idCarrito;
    }
    
    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
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


