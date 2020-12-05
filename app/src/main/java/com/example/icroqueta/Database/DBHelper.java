package com.example.icroqueta.database;

import android.app.Person;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;

import com.example.icroqueta.database.DBSource;
import com.example.icroqueta.database.entidades.*;
import com.example.icroqueta.database.tablas.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DBHelper {


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

    public boolean addUsuario(Context context, String nombre, String apellido, String correo, String pass){
        DBSource db = new DBSource(context);
        Persona usuario = new Persona( null, nombre , apellido, correo, pass, 0);

           long prueba= db.getWritableDatabase().insert(PersonaTable.TABLE_NAME, null, usuario.mapearAContenValues());

        return prueba!=-1;
    }

    public int findUsuario(Context context, String correo, String pass){
        String where = PersonaTable.CORREO + "=?";
        String[] whereArgs = {correo};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(PersonaTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Persona> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Persona().loadPersonaFromCursor(cursor));
        }
        if(lista.isEmpty()){
            //No se encuentra el correo
            return 0;
        }
        for(Persona p: lista){
            if(p.getContrasenya().equals(pass)){
                //Si encuentra el usuario y coinciden las contraseñas nos devuelve la id
                return p.getIdPersona();
            }
        }
        //No coincide la contraseña
        return -1;
    }
}


