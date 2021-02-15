package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.Ingrediente;

public class IngredienteTable {
    public static final String TABLE_NAME = "ingrediente";
    public static final String ID_INGREDIENTE = "id_ingrediente";
    public static final String NOMBRE = "nombre";
    public static final String VEGETARIANO = "vegetariano";
    public static final String GLUTEN = "gluten";
    public static final String LACTOSA = "lactosa";
    public static final String TIPO = "tipo";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_INGREDIENTE + " INTEGER PRIMARY KEY," +
                    NOMBRE + " TEXT," +
                    VEGETARIANO + " INTEGER, " +
                    GLUTEN + " INTEGER," +
                    LACTOSA + " INTEGER," +
                    TIPO + " TEXT)";


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
        Ingrediente i1 = new Ingrediente("Cerdo", 0, 0, 0,"Carne");
        Ingrediente i2 = new Ingrediente("Ternera", 0, 0, 0,"Carne");
        Ingrediente i3 = new Ingrediente("Pollo", 0, 0, 0,"Carne");

        Ingrediente i4 = new Ingrediente("Bacalao", 0, 0, 0,"Pescado");
        Ingrediente i5 = new Ingrediente("Salmón", 0, 0, 0,"Pescado");

        Ingrediente i6 = new Ingrediente("Queso", 1, 0, 1,"Lácteos");
        Ingrediente i7 = new Ingrediente("Leche condensada", 1, 0, 1,"Lácteos");
        Ingrediente i8 = new Ingrediente("Dulce de leche", 1, 0, 1,"Lácteos");

        Ingrediente i9 = new Ingrediente("Patata", 1, 0, 0,"Verduras y hortalizas");
        Ingrediente i10= new Ingrediente("Acelga", 1, 0, 0,"Verduras y hortalizas");

        Ingrediente i11 = new Ingrediente("Tofu", 1, 0, 0,"Legumbres y derivados");
        Ingrediente i12 = new Ingrediente("Garbanzo", 1, 0, 0,"Legumbres y derivados");

        Ingrediente i13 = new Ingrediente("Quinoa", 1, 0, 0,"Semillas");

        Ingrediente i14 = new Ingrediente("Fresa", 1, 0, 0,"Fruta");
        Ingrediente i15 = new Ingrediente("Naranja", 1, 0, 0,"Fruta");

        Ingrediente i16 = new Ingrediente("Chocolate", 1, 0, 0,"Otros");
        Ingrediente i17 = new Ingrediente("Vainilla", 1, 0, 0,"Otros");
        Ingrediente i18 = new Ingrediente("Galleta", 1, 1, 1,"Otros");

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
    }
}