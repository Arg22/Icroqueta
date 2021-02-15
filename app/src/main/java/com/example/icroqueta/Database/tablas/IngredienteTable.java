package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.Ingrediente;

public class IngredienteTable {
    public static final String TABLE_NAME = "ingrediente";
    public static final String ID_INGREDIENTE = "id_ingrediente";
    public static final String NOMBRE = "nombre";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_INGREDIENTE + " INTEGER PRIMARY KEY," +
                    NOMBRE + " TEXT)";


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
        Ingrediente i1 = new Ingrediente("Vegetariano");
        Ingrediente i2 = new Ingrediente("Sin gluten");
        Ingrediente i3 = new Ingrediente("Sin lactosa");
        Ingrediente i4 = new Ingrediente("Cerdo");
        Ingrediente i5 = new Ingrediente("Ternera");
        Ingrediente i6 = new Ingrediente("Pollo");
        Ingrediente i7 = new Ingrediente("Bacalao");
        Ingrediente i8 = new Ingrediente("Salmón");
        Ingrediente i9 = new Ingrediente("Queso");
        Ingrediente i10= new Ingrediente("Leche condensada");
        Ingrediente i11= new Ingrediente("Dulce de leche");
        Ingrediente i12= new Ingrediente("Patata");
        Ingrediente i13= new Ingrediente("Acelga");
        Ingrediente i14 = new Ingrediente("Tofu");
        Ingrediente i15 = new Ingrediente("Garbanzo");
        Ingrediente i16 = new Ingrediente("Quinoa");
        Ingrediente i17 = new Ingrediente("Fresa");
        Ingrediente i18 = new Ingrediente("Naranja");
        Ingrediente i19 = new Ingrediente("Chocolate");
        Ingrediente i20= new Ingrediente("Vainilla");
        Ingrediente i21= new Ingrediente("Galleta");

        db.insert(TABLE_NAME, null, i1.mapearAContenValues());
        db.insert(TABLE_NAME, null, i2.mapearAContenValues());
        db.insert(TABLE_NAME, null, i3.mapearAContenValues());
        db.insert(TABLE_NAME, null, i4.mapearAContenValues());
        db.insert(TABLE_NAME, null, i5.mapearAContenValues());
        db.insert(TABLE_NAME, null, i6.mapearAContenValues());
        db.insert(TABLE_NAME, null, i7.mapearAContenValues());
        db.insert(TABLE_NAME, null, i8.mapearAContenValues());
        db.insert(TABLE_NAME, null, i9.mapearAContenValues());
        db.insert(TABLE_NAME, null, i10.mapearAContenValues());
        db.insert(TABLE_NAME, null, i11.mapearAContenValues());
        db.insert(TABLE_NAME, null, i12.mapearAContenValues());
        db.insert(TABLE_NAME, null, i13.mapearAContenValues());
        db.insert(TABLE_NAME, null, i14.mapearAContenValues());
        db.insert(TABLE_NAME, null, i15.mapearAContenValues());
        db.insert(TABLE_NAME, null, i16.mapearAContenValues());
        db.insert(TABLE_NAME, null, i17.mapearAContenValues());
        db.insert(TABLE_NAME, null, i18.mapearAContenValues());
        db.insert(TABLE_NAME, null, i19.mapearAContenValues());
        db.insert(TABLE_NAME, null, i20.mapearAContenValues());
        db.insert(TABLE_NAME, null, i21.mapearAContenValues());
    }
}