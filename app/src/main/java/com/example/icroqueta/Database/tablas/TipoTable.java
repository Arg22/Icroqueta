package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.Tipo;

public class TipoTable {
    public static final String TABLE_NAME = "tipo";
    public static final String ID_TIPO = "id_ingrediente";
    public static final String NOMBRE = "nombre";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_TIPO + " INTEGER PRIMARY KEY," +
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

        Tipo t1 = new Tipo("Alérgenos");
        Tipo t2 = new Tipo("Carne");
        Tipo t3 = new Tipo("Pescado");
        Tipo t4 = new Tipo("Lácteos");
        Tipo t5 = new Tipo("Verduras y hortalizas");
        Tipo t6 = new Tipo("Legumbres y derivados");
        Tipo t7 = new Tipo("Semillas");
        Tipo t8 = new Tipo("Fruta");
        Tipo t9 = new Tipo("Otros");

        db.insert(TABLE_NAME, null, t1.mapearAContenValues());
        db.insert(TABLE_NAME, null, t2.mapearAContenValues());
        db.insert(TABLE_NAME, null, t3.mapearAContenValues());
        db.insert(TABLE_NAME, null, t4.mapearAContenValues());
        db.insert(TABLE_NAME, null, t5.mapearAContenValues());
        db.insert(TABLE_NAME, null, t6.mapearAContenValues());
        db.insert(TABLE_NAME, null, t7.mapearAContenValues());
        db.insert(TABLE_NAME, null, t8.mapearAContenValues());
        db.insert(TABLE_NAME, null, t9.mapearAContenValues());
    }
}