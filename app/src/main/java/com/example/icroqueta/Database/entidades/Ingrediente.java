package com.example.icroqueta.database.entidades;

public class Ingrediente  implements java.io.Serializable {


     private Integer idIngrediente;
     private String nombre;
     private boolean vegetariano;
     private boolean gluten;
     private boolean lactosa;

    public Ingrediente() {
    }

    public Ingrediente(String nombre, boolean vegetariano, boolean gluten, boolean lactosa) {
       this.nombre = nombre;
       this.vegetariano = vegetariano;
       this.gluten = gluten;
       this.lactosa = lactosa;
    }
   
    public Integer getIdIngrediente() {
        return this.idIngrediente;
    }
    
    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isVegetariano() {
        return this.vegetariano;
    }
    
    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }
    public boolean isGluten() {
        return this.gluten;
    }
    
    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }
    public boolean isLactosa() {
        return this.lactosa;
    }
    
    public void setLactosa(boolean lactosa) {
        this.lactosa = lactosa;
    }




}


