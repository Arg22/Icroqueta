package com.example.icroqueta.database.entidades;

import java.util.HashSet;
import java.util.Set;


public class Persona  implements java.io.Serializable {


     private Integer idPersona;
     private String nif;
     private String nombre;
     private String apellidos;
     private String correo;
     private String contrasenya;
     private boolean rol;
     private Set personaDireccions = new HashSet(0);
     private Set pedidos = new HashSet(0);
     private Set personaTelefonos = new HashSet(0);
     private Set carritos = new HashSet(0);
     private Set personaTarjetas = new HashSet(0);

    public Persona() {
    }

	
    public Persona(String nif, String nombre, String apellidos, String correo, String contrasenya, boolean rol) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenya = contrasenya;
        this.rol = rol;
    }
    public Persona(String nif, String nombre, String apellidos, String correo, String contrasenya, boolean rol, Set personaDireccions, Set pedidos, Set personaTelefonos, Set carritos, Set personaTarjetas) {
       this.nif = nif;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.correo = correo;
       this.contrasenya = contrasenya;
       this.rol = rol;
       this.personaDireccions = personaDireccions;
       this.pedidos = pedidos;
       this.personaTelefonos = personaTelefonos;
       this.carritos = carritos;
       this.personaTarjetas = personaTarjetas;
    }
   
    public Integer getIdPersona() {
        return this.idPersona;
    }
    
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    public String getNif() {
        return this.nif;
    }
    
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContrasenya() {
        return this.contrasenya;
    }
    
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
    public boolean isRol() {
        return this.rol;
    }
    
    public void setRol(boolean rol) {
        this.rol = rol;
    }
    public Set getPersonaDireccions() {
        return this.personaDireccions;
    }
    
    public void setPersonaDireccions(Set personaDireccions) {
        this.personaDireccions = personaDireccions;
    }
    public Set getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set pedidos) {
        this.pedidos = pedidos;
    }
    public Set getPersonaTelefonos() {
        return this.personaTelefonos;
    }
    
    public void setPersonaTelefonos(Set personaTelefonos) {
        this.personaTelefonos = personaTelefonos;
    }
    public Set getCarritos() {
        return this.carritos;
    }
    
    public void setCarritos(Set carritos) {
        this.carritos = carritos;
    }
    public Set getPersonaTarjetas() {
        return this.personaTarjetas;
    }
    
    public void setPersonaTarjetas(Set personaTarjetas) {
        this.personaTarjetas = personaTarjetas;
    }




}


