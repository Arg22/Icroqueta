package com.example.icroqueta.database.entidades;
public class PersonaDireccion  implements java.io.Serializable {


     private Integer idPersonaDireccion;
     private Direccion direccion;
     private Persona persona;

    public PersonaDireccion() {
    }

    public PersonaDireccion(Direccion direccion, Persona persona) {
       this.direccion = direccion;
       this.persona = persona;
    }
   
    public Integer getIdPersonaDireccion() {
        return this.idPersonaDireccion;
    }
    
    public void setIdPersonaDireccion(Integer idPersonaDireccion) {
        this.idPersonaDireccion = idPersonaDireccion;
    }
    public Direccion getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }




}


