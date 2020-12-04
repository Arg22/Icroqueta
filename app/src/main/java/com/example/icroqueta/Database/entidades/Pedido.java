package com.example.icroqueta.database.entidades;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Pedido  implements java.io.Serializable {


     private Integer idPedido;
     private Persona persona;
     private Date fechaPedido;
     private String estado;
     private double importe;
     private Set lineas = new HashSet(0);

    public Pedido() {
    }

	
    public Pedido(Persona persona, Date fechaPedido, String estado, double importe) {
        this.persona = persona;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.importe = importe;
    }
    public Pedido(Persona persona, Date fechaPedido, String estado, double importe, Set lineas) {
       this.persona = persona;
       this.fechaPedido = fechaPedido;
       this.estado = estado;
       this.importe = importe;
       this.lineas = lineas;
    }
   
    public Integer getIdPedido() {
        return this.idPedido;
    }
    
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public Date getFechaPedido() {
        return this.fechaPedido;
    }
    
    public void setFechaPedido(Date fechaPedido) {
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
    public Set getLineas() {
        return this.lineas;
    }
    
    public void setLineas(Set lineas) {
        this.lineas = lineas;
    }




}


