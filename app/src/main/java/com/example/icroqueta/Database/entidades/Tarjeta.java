package com.example.icroqueta.database.entidades;
import java.util.HashSet;
import java.util.Set;


public class Tarjeta  implements java.io.Serializable {


     private Integer idTarjeta;
     private String numero;
     private String fechaCaducidad;
     private int cvc;
     private Set personaTarjetas = new HashSet(0);

    public Tarjeta() {
    }

	
    public Tarjeta(String numero, String fechaCaducidad, int cvc) {
        this.numero = numero;
        this.fechaCaducidad = fechaCaducidad;
        this.cvc = cvc;
    }
    public Tarjeta(String numero, String fechaCaducidad, int cvc, Set personaTarjetas) {
       this.numero = numero;
       this.fechaCaducidad = fechaCaducidad;
       this.cvc = cvc;
       this.personaTarjetas = personaTarjetas;
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
    public int getCvc() {
        return this.cvc;
    }
    
    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
    public Set getPersonaTarjetas() {
        return this.personaTarjetas;
    }
    
    public void setPersonaTarjetas(Set personaTarjetas) {
        this.personaTarjetas = personaTarjetas;
    }




}


