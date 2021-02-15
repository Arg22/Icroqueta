package com.example.icroqueta.database.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.icroqueta.database.tablas.IngredienteTable;

public class Ingrediente implements java.io.Serializable {


    private Integer idIngrediente;
    private String nombre;
    private int vegetariano;
    private int gluten;
    private int lactosa;
    private String tipo;

    public Ingrediente() {
    }

    public Ingrediente(Integer idIngrediente, String nombre, int vegetariano, int gluten, int lactosa, String tipo) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.vegetariano = vegetariano;
        this.gluten = gluten;
        this.lactosa = lactosa;
        this.tipo = tipo;
    }

    public Ingrediente(String nombre, int vegetariano, int gluten, int lactosa, String tipo) {
        this.nombre = nombre;
        this.vegetariano = vegetariano;
        this.gluten = gluten;
        this.lactosa = lactosa;
        this.tipo = tipo;
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

    public int isVegetariano() {
        return this.vegetariano;
    }

    public void setVegetariano(int vegetariano) {
        this.vegetariano = vegetariano;
    }

    public int isGluten() {
        return this.gluten;
    }

    public void setGluten(int gluten) {
        this.gluten = gluten;
    }

    public int isLactosa() {
        return this.lactosa;
    }

    public void setLactosa(int lactosa) {
        this.lactosa = lactosa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Mapear sirve para meter valores y crear un mapa
     * mete en cada columna de la tabla, el dato del objeto
     * (escribe la informacion de la tabla).
     *
     * @return values es el mapa de los productos.
     */
    public ContentValues mapearAContenValues() {
        ContentValues values = new ContentValues();
        values.put(IngredienteTable.ID_INGREDIENTE, idIngrediente);
        values.put(IngredienteTable.NOMBRE, nombre);
        values.put(IngredienteTable.VEGETARIANO, vegetariano);
        values.put(IngredienteTable.GLUTEN, gluten);
        values.put(IngredienteTable.LACTOSA, lactosa);
        values.put(IngredienteTable.TIPO, tipo);
        return values;
    }


    /**
     * Esto sirve para leer de la base de datos y mete los valores
     * en el objeto (lee la informacion de la tabla).
     *
     * @param cursor es lo que se lee de la base de datos.
     * @return un objeto ingrediente.
     */
    public Ingrediente loadIngredienteFromCursor(Cursor cursor) {

        int idIngrediente = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteTable.ID_INGREDIENTE));
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(IngredienteTable.NOMBRE));
        int vegetariano = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteTable.VEGETARIANO));
        int gluten = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteTable.GLUTEN));
        int lactosa = cursor.getInt(cursor.getColumnIndexOrThrow(IngredienteTable.LACTOSA));
        String tipo = cursor.getString(cursor.getColumnIndexOrThrow(IngredienteTable.TIPO));

        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.vegetariano = vegetariano;
        this.gluten = gluten;
        this.lactosa = lactosa;
        this.tipo = tipo;

        return this;
    }
}