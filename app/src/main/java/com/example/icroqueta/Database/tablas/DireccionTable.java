package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

public class DireccionTable {
    public static final String TABLE_NAME = "direccion";
    public static final String ID_DIRECCION = "id_direccion";
    public static final String CALLE = "calle";
    public static final String PORTAL = "portal";
    public static final String PUERTA = "puerta";
    public static final String CODIGO_POSTAL = "codigo_postal";
    public static final String LOCALIDAD = "localidad";
    public static final String COORDENADA = "coordenada";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_DIRECCION + " INTEGER PRIMARY KEY," +
                    CALLE + " TEXT," +
                    PORTAL + " TEXT," +
                    PUERTA + " TEXT," +
                    CODIGO_POSTAL + " TEXT," +
                    LOCALIDAD + " TEXT," +
                    COORDENADA + " TEXT UNIQUE)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}
