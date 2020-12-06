package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.Persona;

import java.util.Date;

public class PedidoTable {
    public static final String TABLE_NAME = "pedido";
    public static final String ID_PEDIDO = "id_pedido";
    public static final String ID_PERSONA = "id_persona";
    public static final String FECHA_PEDIDO = "fecha_pedido";
    public static final String ESTADO = "estado";
    public static final String IMPORTE = "importe";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_PEDIDO + " INTEGER PRIMARY KEY," +
                    ID_PERSONA + " INTEGER," +
                    FECHA_PEDIDO + " DATE, " +
                    ESTADO + " TEXT," +
                    IMPORTE + " REAL,"
                    + " FOREIGN KEY ("+ID_PERSONA+") REFERENCES "+PersonaTable.TABLE_NAME+"("+PersonaTable.ID_PERSONA+")ON DELETE CASCADE);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}



