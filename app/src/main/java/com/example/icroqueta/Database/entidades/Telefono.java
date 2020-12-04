package com.example.icroqueta.database.entidades;

import java.util.HashSet;
import java.util.Set;


public class Telefono  implements java.io.Serializable {


     private Integer idTelefono;
     private int nomero;
     private Set personaTelefonos = new HashSet(0);

    public Telefono() {
    }

	
    public Telefono(int nomero) {
        this.nomero = nomero;
    }
    public Telefono(int nomero, Set personaTelefonos) {
       this.nomero = nomero;
       this.personaTelefonos = personaTelefonos;
    }
   
    public Integer getIdTelefono() {
        return this.idTelefono;
    }
    
    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }
    public int getNomero() {
        return this.nomero;
    }
    
    public void setNomero(int nomero) {
        this.nomero = nomero;
    }
    public Set getPersonaTelefonos() {
        return this.personaTelefonos;
    }
    
    public void setPersonaTelefonos(Set personaTelefonos) {
        this.personaTelefonos = personaTelefonos;
    }




}


