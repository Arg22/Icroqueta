package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

public class TarjetaTable {
    public static final String TABLE_NAME = "tarjeta";
    public static final String ID_TARJETA = "id_tarjeta";
    public static final String FECHA_CADUCIDAD = "fecha_caducidad";
    public static final String NUMERO = "numero";
    public static final String CVC = "cvc";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_TARJETA + " INTEGER PRIMARY KEY," +
                    FECHA_CADUCIDAD + " DATE," +
                    NUMERO + " TEXT," +
                    CVC + " INTEGER)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }
}
