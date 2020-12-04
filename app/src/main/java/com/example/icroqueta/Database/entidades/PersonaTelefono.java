package com.example.icroqueta.database.entidades;
public class PersonaTelefono  implements java.io.Serializable {


     private Integer idPersonaTelefono;
     private Persona persona;
     private Telefono telefono;

    public PersonaTelefono() {
    }

    public PersonaTelefono(Persona persona, Telefono telefono) {
       this.persona = persona;
       this.telefono = telefono;
    }
   
    public Integer getIdPersonaTelefono() {
        return this.idPersonaTelefono;
    }
    
    public void setIdPersonaTelefono(Integer idPersonaTelefono) {
        this.idPersonaTelefono = idPersonaTelefono;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public Telefono getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }




}


