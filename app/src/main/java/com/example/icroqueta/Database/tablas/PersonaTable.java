package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.Persona;

public class PersonaTable {
    public static final String TABLE_NAME = "persona";
    public static final String ID_PERSONA = "id_persona";
    public static final String NIF = "nif";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDOS = "apellidos";
    public static final String CORREO = "correo";
    public static final String CONTRASENA = "contraseña";
    public static final String ROL = "rol";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_PERSONA + " INTEGER PRIMARY KEY," +
                    NIF + " TEXT," +
                    NOMBRE + " TEXT, " +
                    APELLIDOS + " TEXT," +
                    CORREO + " TEXT UNIQUE," +
                    CONTRASENA + " TEXT," +
                    ROL + " INTEGER)";

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
        Persona p = new Persona("87654321X", "Nombre de prueba", "Apellido de Prueba", "a", "86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", 0);
        Persona p2 = new Persona("12345678Z", "Soy el Root", "Root", "b", "e9d71f5ee7c92d6dc9e92ffdad17b8bd49418f98", 1);

        db.insert(TABLE_NAME, null, p.mapearAContenValues());
        db.insert(TABLE_NAME, null, p2.mapearAContenValues());
    }

}
