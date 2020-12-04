package com.example.icroqueta.presentador;

import android.content.Context;
import android.database.Cursor;

import com.example.icroqueta.database.DBSource;
import com.example.icroqueta.database.entidades.Producto;
import com.example.icroqueta.database.tablas.ProductoTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainPresenter {


    /**Este método sirve para hacer un Select All de los productos
     * y los mete en una lista
     *
     * @param context el contexto de la actividad
     * @return la lista de los productos
     */
    public List<Producto> leerProductos(Context context) {
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, null, null, null, null, null);
        List<Producto> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Producto().loadProductoFromCursor(cursor));
        }
        cursor.close();
        return lista;
    }


}


