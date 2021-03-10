package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;


public class LineaTable {
    public static final String TABLE_NAME = "linea";
    public static final String ID_LINEA = "id_linea";
    public static final String ID_PEDIDO = "id_pedido";
    public static final String ID_PRODUCTO = "id_producto";
    public static final String CANTIDAD = "cantidad";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_LINEA + " INTEGER PRIMARY KEY," +
                    ID_PRODUCTO + " INTEGER," +
                    ID_PEDIDO + " INTEGER," +
                    CANTIDAD + " INTEGER," +
                    " FOREIGN KEY (" + ID_PRODUCTO + ") REFERENCES " + ProductoTable.TABLE_NAME + "(" + ProductoTable.ID_PRODUCTO + ")ON DELETE CASCADE," +
                    " FOREIGN KEY (" + ID_PEDIDO + ") REFERENCES " + PedidoTable.TABLE_NAME + "(" + PedidoTable.ID_PEDIDO + ")ON DELETE CASCADE);";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }


}
