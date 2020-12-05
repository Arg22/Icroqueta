package com.example.icroqueta.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.icroqueta.database.entidades.Persona;
import com.example.icroqueta.database.tablas.PersonaTable;
import com.example.icroqueta.database.tablas.ProductoTable;

public class DBSource extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "croqueta.db";
    public SQLiteDatabase db;
    public DBSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db; //todo preguntar por esto
        ProductoTable.onCreate(db);
        PersonaTable.onCreate(db);
    }

    /**
     * Borra la tabla y la vuelve a inicializar cuando se suma la version
     *
     * @param db         nuestra base de datos
     * @param oldVersion la version anterior
     * @param newVersion la version actual
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ProductoTable.onDrop(db);
        PersonaTable.onDrop(db);
        onCreate(db);
    }


    /**
     * Borra la tabla y la vuelve a inicializar cuando se baja la version
     *
     * @param db         nuestra base de datos
     * @param oldVersion la version anterior
     * @param newVersion la version actual
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ProductoTable.onDrop(db);
        PersonaTable.onDrop(db);
        onCreate(db);
    }


}
