package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

public class PersonaDireccionTable {
    public static final String TABLE_NAME = "persona_Direccion";
    public static final String ID_PERSONA_Direccion = "id_persona_Direccion";
    public static final String ID_PERSONA = "id_persona";
    public static final String ID_DIRECCION = "id_Direccion";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_PERSONA_Direccion + " INTEGER PRIMARY KEY," +
                    ID_PERSONA + " INTEGER," +
                    ID_DIRECCION + " INTEGER, " +
                    " FOREIGN KEY (" + ID_PERSONA + ") REFERENCES " + PersonaTable.TABLE_NAME + "(" + PersonaTable.ID_PERSONA + ")ON DELETE CASCADE," +
                    " FOREIGN KEY (" + ID_DIRECCION + ") REFERENCES " + DireccionTable.TABLE_NAME + "(" + DireccionTable.ID_DIRECCION + ")ON DELETE CASCADE);";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}
