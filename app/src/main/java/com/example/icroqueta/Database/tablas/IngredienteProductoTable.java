package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.IngredienteProducto;

public class IngredienteProductoTable {
    public static final String TABLE_NAME = "ingrediente_producto";
    public static final String ID_INGREDIENTE_PRODUCTO = "id_ingredienteProducto";
    public static final String ID_INGREDIENTE = "id_ingrediente";
    public static final String ID_PRODUCTO = "id_producto";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_INGREDIENTE_PRODUCTO + " INTEGER PRIMARY KEY," +
                    ID_INGREDIENTE + " INTEGER," +
                    ID_PRODUCTO + " INTEGER, " +
                    " FOREIGN KEY (" + ID_INGREDIENTE + ") REFERENCES " + IngredienteTable.TABLE_NAME + "(" + IngredienteTable.ID_INGREDIENTE + ")ON DELETE CASCADE," +
                    " FOREIGN KEY (" + ID_PRODUCTO + ") REFERENCES " + ProductoTable.TABLE_NAME + "(" + ProductoTable.ID_PRODUCTO + ")ON DELETE CASCADE);";


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
        IngredienteProducto i1 = new IngredienteProducto(1, 1);
        IngredienteProducto i2 = new IngredienteProducto(1, 6);
        IngredienteProducto i3 = new IngredienteProducto(2, 1);
        IngredienteProducto i4 = new IngredienteProducto(2, 6);
        IngredienteProducto i5 = new IngredienteProducto(3, 3);
        IngredienteProducto i6 = new IngredienteProducto(4, 11);
        IngredienteProducto i7 = new IngredienteProducto(5, 4);
        IngredienteProducto i8 = new IngredienteProducto(6, 5);
        IngredienteProducto i9 = new IngredienteProducto(7, 5);
        IngredienteProducto i10 = new IngredienteProducto(8, 13);
        IngredienteProducto i11 = new IngredienteProducto(9, 6);
        IngredienteProducto i12 = new IngredienteProducto(10, 6);
        IngredienteProducto i13 = new IngredienteProducto(11, 10);
        IngredienteProducto i14 = new IngredienteProducto(12, 17);
        IngredienteProducto i15 = new IngredienteProducto(12, 8);
        IngredienteProducto i16 = new IngredienteProducto(13, 16);
        IngredienteProducto i17 = new IngredienteProducto(13, 8);
        IngredienteProducto i18 = new IngredienteProducto(14, 14);

        db.insert(TABLE_NAME, null, i1 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i2 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i3 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i4 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i5 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i6 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i7 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i8 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i9 .mapearAContenValues());
        db.insert(TABLE_NAME, null, i10.mapearAContenValues());
        db.insert(TABLE_NAME, null, i11.mapearAContenValues());
        db.insert(TABLE_NAME, null, i12.mapearAContenValues());
        db.insert(TABLE_NAME, null, i13.mapearAContenValues());
        db.insert(TABLE_NAME, null, i14.mapearAContenValues());
        db.insert(TABLE_NAME, null, i15.mapearAContenValues());
        db.insert(TABLE_NAME, null, i16.mapearAContenValues());
        db.insert(TABLE_NAME, null, i17.mapearAContenValues());
        db.insert(TABLE_NAME, null, i18.mapearAContenValues());
    }
}