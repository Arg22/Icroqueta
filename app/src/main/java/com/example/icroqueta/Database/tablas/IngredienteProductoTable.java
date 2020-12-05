package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.IngredienteProducto;


public class IngredienteProductoTable {
    public static final String TABLE_NAME = "ingrediente_producto";
    public static final String ID_INGREDIENTE_PRODUCTO = "id_persona_tarjeta";
    public static final String ID_INGREDIENTE = "id_persona";
    public static final String ID_PRODUCTO= "id_tarjeta";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_INGREDIENTE_PRODUCTO + " INTEGER PRIMARY KEY," +
                    ID_INGREDIENTE + " INTEGER," +
                    ID_PRODUCTO + " INTEGER, " +
                    " FOREIGN KEY (" + ID_INGREDIENTE + ") REFERENCES " + IngredienteTable.TABLE_NAME + "(" + IngredienteTable.ID_INGREDIENTE + ")," +
                    " FOREIGN KEY (" + ID_PRODUCTO + ") REFERENCES " + ProductoTable.TABLE_NAME + "(" + ProductoTable.ID_PRODUCTO + "));";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }


    /**Aqui se insertarian por primera vez los valores de la tabla
     *
     * @param db nuestra base de datos
     */
    public static void insertIniciales(SQLiteDatabase db) {
        IngredienteProducto i = new IngredienteProducto(1,2);
        IngredienteProducto i = new IngredienteProducto(1,2);


        db.insert(TABLE_NAME, null, i.mapearAContenValues());
        db.insert(TABLE_NAME, null, i2.mapearAContenValues());
    }

}