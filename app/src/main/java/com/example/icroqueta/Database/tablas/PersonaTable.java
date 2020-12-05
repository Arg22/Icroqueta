package com.example.icroqueta.database.tablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.DBSource;
import com.example.icroqueta.database.entidades.Persona;
import com.example.icroqueta.database.entidades.Producto;

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


    /**Aqui se insertarian por primera vez los valores de la tabla
     *
     * @param db nuestra base de datos
     */
    public static void insertIniciales(SQLiteDatabase db) {
        Persona p = new Persona("123456878Q", "Godofredo", "Gofrero", "a", "a", 0);
        Persona p2 = new Persona("123456878A", "Agata", "LaGata", "b", "b", 1);

        db.insert(TABLE_NAME, null, p.mapearAContenValues());
        db.insert(TABLE_NAME, null, p2.mapearAContenValues());
    }

}
