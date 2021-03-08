package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

public class TelefonoTable {
    public static final String TABLE_NAME = "telefono";
    public static final String ID_TELEFONO = "id_telefono";
    public static final String NUMERO = "numero";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_TELEFONO + " INTEGER PRIMARY KEY," +
                    NUMERO + " TEXT UNIQUE)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }
}
