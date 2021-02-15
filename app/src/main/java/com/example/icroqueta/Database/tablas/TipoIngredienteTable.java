package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.TipoIngrediente;

public class TipoIngredienteTable {
    public static final String TABLE_NAME = "tipo_ingrediente";
    public static final String ID_TIPO_INGREDIENTE = "id_tipo_ingrediente";
    public static final String ID_INGREDIENTE = "id_ingrediente";
    public static final String ID_TIPO = "id_tipo";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_TIPO_INGREDIENTE + " INTEGER PRIMARY KEY," +
                    ID_INGREDIENTE + " INTEGER," +
                    ID_TIPO + " INTEGER, " +
                    " FOREIGN KEY (" + ID_INGREDIENTE + ") REFERENCES " + IngredienteTable.TABLE_NAME + "(" + IngredienteTable.ID_INGREDIENTE + ")ON DELETE CASCADE," +
                    " FOREIGN KEY (" + ID_TIPO + ") REFERENCES " + TipoTable.TABLE_NAME + "(" + TipoTable.ID_TIPO + ")ON DELETE CASCADE);";


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
        //Tipo alergeno
        TipoIngrediente i1 = new TipoIngrediente(1, 1);
        TipoIngrediente i2 = new TipoIngrediente(1, 2);
        TipoIngrediente i3 = new TipoIngrediente(1, 3);
        //Tipo carne
        TipoIngrediente i4 = new TipoIngrediente(2, 4);
        TipoIngrediente i5 = new TipoIngrediente(2, 5);
        TipoIngrediente i6 = new TipoIngrediente(2, 6);
        //Tipo pescado
        TipoIngrediente i7 = new TipoIngrediente(3, 7);
        TipoIngrediente i8 = new TipoIngrediente(3, 8);
        //Tipo lacteos
        TipoIngrediente i9 = new TipoIngrediente(4, 9);
        TipoIngrediente i10 = new TipoIngrediente(4, 10);
        TipoIngrediente i11 = new TipoIngrediente(4, 11);
        // Tipo Verduras y hortalizas
        TipoIngrediente i12 = new TipoIngrediente(5, 12);
        TipoIngrediente i13 = new TipoIngrediente(5, 13);
        // Legumbres y derivados
        TipoIngrediente i14 = new TipoIngrediente(6, 14);
        TipoIngrediente i15 = new TipoIngrediente(6, 15);
        //Tipo Semillas
        TipoIngrediente i16 = new TipoIngrediente(7, 16);
        //Tipo Frutas
        TipoIngrediente i17 = new TipoIngrediente(8, 17);
        TipoIngrediente i18 = new TipoIngrediente(8, 18);
        //Tipo Otros
        TipoIngrediente i19 = new TipoIngrediente(9, 19);
        TipoIngrediente i20 = new TipoIngrediente(9, 20);
        TipoIngrediente i21 = new TipoIngrediente(9, 21);

        db.insert(TABLE_NAME, null, i1.mapearAContenValues());
        db.insert(TABLE_NAME, null, i2.mapearAContenValues());
        db.insert(TABLE_NAME, null, i3.mapearAContenValues());
        db.insert(TABLE_NAME, null, i4.mapearAContenValues());
        db.insert(TABLE_NAME, null, i5.mapearAContenValues());
        db.insert(TABLE_NAME, null, i6.mapearAContenValues());
        db.insert(TABLE_NAME, null, i7.mapearAContenValues());
        db.insert(TABLE_NAME, null, i8.mapearAContenValues());
        db.insert(TABLE_NAME, null, i9.mapearAContenValues());
        db.insert(TABLE_NAME, null, i10.mapearAContenValues());
        db.insert(TABLE_NAME, null, i11.mapearAContenValues());
        db.insert(TABLE_NAME, null, i12.mapearAContenValues());
        db.insert(TABLE_NAME, null, i13.mapearAContenValues());
        db.insert(TABLE_NAME, null, i14.mapearAContenValues());
        db.insert(TABLE_NAME, null, i15.mapearAContenValues());
        db.insert(TABLE_NAME, null, i16.mapearAContenValues());
        db.insert(TABLE_NAME, null, i17.mapearAContenValues());
        db.insert(TABLE_NAME, null, i18.mapearAContenValues());
        db.insert(TABLE_NAME, null, i19.mapearAContenValues());
        db.insert(TABLE_NAME, null, i20.mapearAContenValues());
        db.insert(TABLE_NAME, null, i21.mapearAContenValues());
    }
}