package com.example.icroqueta.database.entidades;
public class PersonaTarjeta  implements java.io.Serializable {


     private Integer idPersonaTarjeta;
     private Persona persona;
     private Tarjeta tarjeta;

    public PersonaTarjeta() {
    }

    public PersonaTarjeta(Persona persona, Tarjeta tarjeta) {
       this.persona = persona;
       this.tarjeta = tarjeta;
    }
   
    public Integer getIdPersonaTarjeta() {
        return this.idPersonaTarjeta;
    }
    
    public void setIdPersonaTarjeta(Integer idPersonaTarjeta) {
        this.idPersonaTarjeta = idPersonaTarjeta;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public Tarjeta getTarjeta() {
        return this.tarjeta;
    }
    
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }




}


