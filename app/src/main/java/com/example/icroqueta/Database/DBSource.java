package com.example.icroqueta.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.icroqueta.database.entidades.IngredienteProducto;
import com.example.icroqueta.database.entidades.Persona;
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

public class DBSource extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "croqueta.db";

    public DBSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ProductoTable.onCreate(db);
        PersonaTable.onCreate(db);
        DireccionTable.onCreate(db);
        IngredienteTable.onCreate(db);
        PedidoTable.onCreate(db);
        TarjetaTable.onCreate(db);
        TelefonoTable.onCreate(db);
        PersonaTelefonoTable.onCreate(db);
        PersonaTarjetaTable.onCreate(db);
        PersonaDireccionTable.onCreate(db);
        LineaTable.onCreate(db);
        CarritoTable.onCreate(db);
        IngredienteProductoTable.onCreate(db);
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
        PersonaTelefonoTable.onDrop(db);
        PersonaTarjetaTable.onDrop(db);
        PersonaDireccionTable.onDrop(db);
        LineaTable.onDrop(db);
        CarritoTable.onDrop(db);
        IngredienteProductoTable.onDrop(db);
        ProductoTable.onDrop(db);
        PersonaTable.onDrop(db);
        DireccionTable.onDrop(db);
        IngredienteTable.onDrop(db);
        PedidoTable.onDrop(db);
        TarjetaTable.onDrop(db);
        TelefonoTable.onDrop(db);
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
        PersonaTelefonoTable.onDrop(db);
        PersonaTarjetaTable.onDrop(db);
        PersonaDireccionTable.onDrop(db);
        LineaTable.onDrop(db);
        CarritoTable.onDrop(db);
        IngredienteProductoTable.onDrop(db);
        ProductoTable.onDrop(db);
        PersonaTable.onDrop(db);
        DireccionTable.onDrop(db);
        IngredienteTable.onDrop(db);
        PedidoTable.onDrop(db);
        TarjetaTable.onDrop(db);
        TelefonoTable.onDrop(db);
        onCreate(db);
    }


}
