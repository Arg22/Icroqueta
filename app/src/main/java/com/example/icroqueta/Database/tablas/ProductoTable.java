package com.example.icroqueta.database.tablas;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.icroqueta.database.entidades.Producto;

public class ProductoTable {
    public static final String TABLE_NAME = "Producto";
    public static final String ID_PRODCUTO = "id_producto";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String PRECIO_UD = "precio_ud";
    public static final String STOCK = "stock";
    public static final String DESCUENTO = "descuento";
    public static final String IMAGEN = "imagen";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_PRODCUTO + " INTEGER PRIMARY KEY," +
                    NOMBRE + " TEXT," +
                    DESCRIPCION + " TEXT, " +
                    PRECIO_UD + " REAL," +
                    STOCK + " INTEGER," +
                    DESCUENTO + " REAL," +
                    IMAGEN + " TEXT)";

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
        Producto p = new Producto("Jamon y queso", "Croquetas con delicioso jamón dulce y queso curado", 1.05, 100, 0, "https://i.ytimg.com/vi/HyAPon0ISvc/maxresdefault.jpg");
        Producto p2 = new Producto("Vainilla y cholate", "Croquetas dulces con leche condensada", 2.45, 50, 0, "https://canalcocina.es/medias/_cache/zoom-f46025d0bc49437cc3227d0ef9381d15-920-518.jpg");
        Producto p3 = new Producto("Salmón ahumado", "Rico salmón cocinado a la piedra", 1.75, 10, 0, "https://www.divinacocina.es/wp-content/uploads/croquetas-de-salmon.jpg");

        db.insert(TABLE_NAME, null, p.mapearAContenValues());
        db.insert(TABLE_NAME, null, p2.mapearAContenValues());
        db.insert(TABLE_NAME, null, p3.mapearAContenValues());
    }
}
