package com.example.icroqueta.database.entidades;


import java.util.HashSet;
import java.util.Set;

public class Direccion  implements java.io.Serializable {


     private Integer idDireccion;
     private String calle;
     private String ciudad;
     private String provincia;
     private String codigoPostal;
     private Set personaDireccions = new HashSet(0);
     //todo coordenada

    public Direccion() {
    }

	
    public Direccion(String calle, String ciudad, String provincia, String codigoPostal) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }
    public Direccion(String calle, String ciudad, String provincia, String codigoPostal, Set personaDireccions) {
       this.calle = calle;
       this.ciudad = ciudad;
       this.provincia = provincia;
       this.codigoPostal = codigoPostal;
       this.personaDireccions = personaDireccions;
    }
   
    public Integer getIdDireccion() {
        return this.idDireccion;
    }
    
    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getCodigoPostal() {
        return this.codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public Set getPersonaDireccions() {
        return this.personaDireccions;
    }
    
    public void setPersonaDireccions(Set personaDireccions) {
        this.personaDireccions = personaDireccions;
    }




}


