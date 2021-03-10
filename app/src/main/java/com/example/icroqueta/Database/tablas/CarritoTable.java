package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

public class CarritoTable {
    public static final String TABLE_NAME = "carrito";
    public static final String ID_CARRITO = "id_carrito";
    public static final String ID_PERSONA = "id_persona";
    public static final String ID_PRODUCTO = "id_producto";
    public static final String CANTIDAD = "cantidad";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_CARRITO + " INTEGER PRIMARY KEY," +
                    ID_PRODUCTO + " INTEGER," +
                    ID_PERSONA + " INTEGER," +
                    CANTIDAD + " INTEGER," +
                    " FOREIGN KEY (" + ID_PRODUCTO + ") REFERENCES " + ProductoTable.TABLE_NAME + "(" + ProductoTable.ID_PRODUCTO + ") ON DELETE CASCADE," +
                    " FOREIGN KEY (" + ID_PERSONA + ") REFERENCES " + PersonaTable.TABLE_NAME + "(" + PersonaTable.ID_PERSONA + ")ON DELETE CASCADE);";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }


}
