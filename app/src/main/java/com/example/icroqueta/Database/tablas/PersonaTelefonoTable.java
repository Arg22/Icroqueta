package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

public class PersonaTelefonoTable {
    public static final String TABLE_NAME = "persona_telefono";
    public static final String ID_PERSONA_TELEFONO = "id_persona_telefono";
    public static final String ID_PERSONA = "id_persona";
    public static final String ID_TELEFONO = "id_telefono";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_PERSONA_TELEFONO + " INTEGER PRIMARY KEY," +
                    ID_PERSONA + " INTEGER," +
                    ID_TELEFONO + " INTEGER, " +
                    " FOREIGN KEY (" + ID_PERSONA + ") REFERENCES " + PersonaTable.TABLE_NAME + "(" + PersonaTable.ID_PERSONA + ")ON DELETE CASCADE," +
                    " FOREIGN KEY (" + ID_TELEFONO + ") REFERENCES " + TelefonoTable.TABLE_NAME + "(" + TelefonoTable.ID_TELEFONO + ")ON DELETE CASCADE);";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}
