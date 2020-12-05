package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.Ingrediente;
import com.example.icroqueta.database.entidades.Persona;

public class IngredienteTable {
    public static final String TABLE_NAME = "ingrediente";
    public static final String ID_INGREDIENTE = "id_ingrediente";
    public static final String NOMBRE = "nombre";
    public static final String VEGETARIANO = "vegetariano";
    public static final String GLUTEN = "gluten";
    public static final String LACTOSA = "lactosa";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_INGREDIENTE + " INTEGER PRIMARY KEY," +
                    NOMBRE + " TEXT," +
                    VEGETARIANO + " INTEGER, " +
                    GLUTEN + " INTEGER," +
                    LACTOSA + " INTEGER)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        insertIniciales(db);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }


    /**
     * Aqui se insertarian por primera vez los valores de la tabla
     *
     * @param db nuestra base de datos
     */
    public static void insertIniciales(SQLiteDatabase db) {
        Ingrediente i1 = new Ingrediente("Bacalao", 0, 0, 0);
        Ingrediente i2 = new Ingrediente("Cerdo", 0, 0, 0);
        Ingrediente i3 = new Ingrediente("Ternera", 0, 0, 0);
        Ingrediente i4 = new Ingrediente("Pollo", 0, 0, 0);
        Ingrediente i5 = new Ingrediente("Salmon", 0, 0, 0);
        Ingrediente i6 = new Ingrediente("Queso", 1, 0, 1);
        Ingrediente i7 = new Ingrediente("Leche condensada", 1, 0, 1);
        Ingrediente i8 = new Ingrediente("Queso sin lactosa", 1, 0, 1);
        Ingrediente i9 = new Ingrediente("Harina sin gluten", 1, 1, 0);
        Ingrediente i10 = new Ingrediente("Tofu", 1, 0, 0);
        Ingrediente i11 = new Ingrediente("Harina de trigo", 1, 0, 0);
        Ingrediente i12 = new Ingrediente("Quinoa", 1, 0, 0);
        Ingrediente i13 = new Ingrediente("Patata", 1, 0, 0);
        Ingrediente i14 = new Ingrediente("Fresas", 1, 0, 0);
        Ingrediente i15 = new Ingrediente("Acelgas", 1, 0, 0);
        Ingrediente i16 = new Ingrediente("Chocolate", 1, 0, 0);
        Ingrediente i17 = new Ingrediente("Vainilla", 1, 0, 0);
        Ingrediente i18 = new Ingrediente("Oreo", 1, 1, 1);

        db.insert(TABLE_NAME, null, i1 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i2 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i3 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i4 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i5 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i6 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i7 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i8 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i9 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i10.mapearAContenValues());
        db.insert(TABLE_NAME, null, i11.mapearAContenValues());
        db.insert(TABLE_NAME, null, i12.mapearAContenValues());
        db.insert(TABLE_NAME, null, i13.mapearAContenValues());
        db.insert(TABLE_NAME, null, i14.mapearAContenValues());
        db.insert(TABLE_NAME, null, i15.mapearAContenValues());
        db.insert(TABLE_NAME, null, i16.mapearAContenValues());
        db.insert(TABLE_NAME, null, i17.mapearAContenValues());
        db.insert(TABLE_NAME, null, i18.mapearAContenValues());

    }

}

