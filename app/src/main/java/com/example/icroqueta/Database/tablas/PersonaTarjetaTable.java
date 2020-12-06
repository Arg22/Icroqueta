package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

public class PersonaTarjetaTable {
    public static final String TABLE_NAME = "persona_tarjeta";
    public static final String ID_PERSONA_TARJETA = "id_persona_tarjeta";
    public static final String ID_PERSONA = "id_persona";
    public static final String ID_TARJETA= "id_tarjeta";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_PERSONA_TARJETA + " INTEGER PRIMARY KEY," +
                    ID_PERSONA + " INTEGER," +
                    ID_TARJETA + " INTEGER, " +
                    " FOREIGN KEY (" + ID_PERSONA + ") REFERENCES " + PersonaTable.TABLE_NAME + "(" + PersonaTable.ID_PERSONA + ")ON DELETE CASCADE," +
                    " FOREIGN KEY (" + ID_TARJETA + ") REFERENCES " + TarjetaTable.TABLE_NAME + "(" + TarjetaTable.ID_TARJETA + ")ON DELETE CASCADE);";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}
