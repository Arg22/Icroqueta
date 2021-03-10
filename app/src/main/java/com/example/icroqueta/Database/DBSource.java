package com.example.icroqueta.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.icroqueta.database.tablas.CarritoTable;
import com.example.icroqueta.database.tablas.DireccionTable;
import com.example.icroqueta.database.tablas.IngredienteProductoTable;
import com.example.icroqueta.database.tablas.IngredienteTable;
import com.example.icroqueta.database.tablas.LineaTable;
import com.example.icroqueta.database.tablas.PedidoTable;
import com.example.icroqueta.database.tablas.PersonaDireccionTable;
import com.example.icroqueta.database.tablas.PersonaTable;
import com.example.icroqueta.database.tablas.PersonaTarjetaTable;
import com.example.icroqueta.database.tablas.PersonaTelefonoTable;
import com.example.icroqueta.database.tablas.ProductoTable;
import com.example.icroqueta.database.tablas.TarjetaTable;
import com.example.icroqueta.database.tablas.TelefonoTable;
import com.example.icroqueta.database.tablas.TipoIngredienteTable;
import com.example.icroqueta.database.tablas.TipoTable;

public class DBSource extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "croqueta.db";

    public DBSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CarritoTable.onCreate(db);
        DireccionTable.onCreate(db);
        IngredienteProductoTable.onCreate(db);
        IngredienteTable.onCreate(db);
        LineaTable.onCreate(db);
        PedidoTable.onCreate(db);
        PersonaDireccionTable.onCreate(db);
        PersonaTable.onCreate(db);
        PersonaTarjetaTable.onCreate(db);
        PersonaTelefonoTable.onCreate(db);
        ProductoTable.onCreate(db);
        TarjetaTable.onCreate(db);
        TelefonoTable.onCreate(db);
        TipoIngredienteTable.onCreate(db);
        TipoTable.onCreate(db);
    }

    /**
     * Metodo para que la base dato detecte las foreign key
     *
     * @param db nuestra base de datos
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    /**
     * Borra la tabla y la vuelve a inicializar cuando aumenta la version
     *
     * @param db         nuestra base de datos
     * @param oldVersion la version anterior
     * @param newVersion la version actual
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CarritoTable.onDrop(db);
        DireccionTable.onDrop(db);
        IngredienteProductoTable.onDrop(db);
        IngredienteTable.onDrop(db);
        LineaTable.onDrop(db);
        PedidoTable.onDrop(db);
        PersonaDireccionTable.onDrop(db);
        PersonaTable.onDrop(db);
        PersonaTarjetaTable.onDrop(db);
        PersonaTelefonoTable.onDrop(db);
        ProductoTable.onDrop(db);
        TarjetaTable.onDrop(db);
        TelefonoTable.onDrop(db);
        TipoIngredienteTable.onDrop(db);
        TipoTable.onDrop(db);
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
        CarritoTable.onDrop(db);
        DireccionTable.onDrop(db);
        IngredienteProductoTable.onDrop(db);
        IngredienteTable.onDrop(db);
        LineaTable.onDrop(db);
        PedidoTable.onDrop(db);
        PersonaDireccionTable.onDrop(db);
        PersonaTable.onDrop(db);
        PersonaTarjetaTable.onDrop(db);
        PersonaTelefonoTable.onDrop(db);
        ProductoTable.onDrop(db);
        TarjetaTable.onDrop(db);
        TelefonoTable.onDrop(db);
        TipoIngredienteTable.onDrop(db);
        TipoTable.onDrop(db);
        onCreate(db);
    }
}