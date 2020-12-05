package com.example.icroqueta.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.example.icroqueta.database.entidades.*;
import com.example.icroqueta.database.tablas.*;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {


    /**
     * Este método sirve para hacer un Select All de los productos
     * y los mete en una lista
     *
     * @param context el contexto de la actividad
     * @return la lista de los productos
     */
    public List<Producto> findAllProductos(Context context) {
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, null, null, null, null, null);
        List<Producto> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Producto().loadProductoFromCursor(cursor));
        }
        cursor.close();
        return lista;
    }

    /**
     * Metodo para obtener un unico producto por su id
     *
     * @param context el contexto de la actividad
     * @param id      la id del producto para buscarlo
     * @return le producto con esa id
     */
    public Producto findProducto(Context context, int id) {
        String where = ProductoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {id + ""};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Producto> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Producto().loadProductoFromCursor(cursor));
        }
        return lista.get(0);
    }

    /**
     * Metodo para añadir un registro a la base de datos Persona
     *
     * @param context  el contexto de la actividad
     * @param nif      la id de la persona
     * @param nombre   el nombre de la persona
     * @param apellido el apellido de la persona
     * @param correo   el correo de la persona
     * @param pass     la contraseña de la persona
     * @return true si se ha añadido bien y false si ha habido un problema
     */
    public boolean addPersona(Context context, String nif, String nombre, String apellido, String correo, String pass) {
        DBSource db = new DBSource(context);
        Persona usuario = new Persona(nif, nombre, apellido, correo, pass, 0);
        long prueba = db.getWritableDatabase().insert(PersonaTable.TABLE_NAME, null, usuario.mapearAContenValues());
        return prueba != -1;
    }

    /**
     * Metodo para el loggin de la aplicacion
     *
     * @param context el contexto de la actividad
     * @param correo  el correo de la persona
     * @param pass    la contraseña de la persona
     * @return 0 si no existe el correo en la bd, -1 si no coincide la
     * contraseña o la Id del usuario si se ha loggeado correctamente
     */
    public int findPersonaLogin(Context context, String correo, String pass) {
        String where = PersonaTable.CORREO + "=?";
        String[] whereArgs = {correo};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(PersonaTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Persona> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Persona().loadPersonaFromCursor(cursor));
        }
        if (lista.isEmpty()) {
            //No se encuentra el correo
            return 0;
        }
        for (Persona p : lista) {
            if (p.getContrasenya().equals(pass)) {
                //Si encuentra el usuario y coinciden las contraseñas nos devuelve la id
                return p.getIdPersona();
            }
        }
        //No coincide la contraseña
        return -1;
    }

    /**
     * Metodo para encontrar a un usuario por su id
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id por el que vamos a buscarlo
     * @return un objeto del tipo Persona
     */
    public Persona findPersonaId(Context context, int idPersona) {
        String where = PersonaTable.ID_PERSONA + "=?";
        String[] whereArgs = {idPersona + ""};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(PersonaTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Persona> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Persona().loadPersonaFromCursor(cursor));
        }
        return lista.get(0);
    }

    /**
     * Metodo para escrribir una linea del producto al realizar le pedido
     *
     * @param context    el contexto de la actividad
     * @param idProducto el id del producto
     * @param idPedido   el id del pedido
     * @param cantidad   la cantidad del producto
     */
    public void addLinea(Context context, int idProducto, int idPedido, int cantidad) {
        DBSource db = new DBSource(context);
        Linea linea = new Linea(idProducto, idPedido, cantidad);
        db.getWritableDatabase().insert(LineaTable.TABLE_NAME, null, linea.mapearAContenValues());
    }

    /**
     * Metodo para añadir un producto al carrito para guardarlo cuando cierre la app
     *
     * @param context    el contexto de la actividad
     * @param idProducto el id del producto
     * @param idPersona  el id del usuario
     * @param cantidad   la cantidad del producto
     */
    public void addCarrito(Context context, int idPersona, int idProducto, int cantidad) {
        DBSource db = new DBSource(context);
        Carrito linea = new Carrito(idPersona, idProducto, cantidad);
        db.getWritableDatabase().insert(CarritoTable.TABLE_NAME, null, linea.mapearAContenValues());
    }

    /**
     * Metodo para comprobar si existe un determinado producto en el carrito
     *
     * @param context    el contexto de la actividad
     * @param idProducto el id del producto
     * @param idPersona  el id del usuario
     * @return true si existe, false si no existe en la bd
     */
    public boolean existCarritoProducto(Context context, int idPersona, int idProducto) {
        String where = CarritoTable.ID_PERSONA + "=? AND " + CarritoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {idPersona + "", idProducto + ""};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(CarritoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        return cursor.getCount() != 0;

    }

    /**
     * Metodo para borrar todos los registros del carro
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     */
    public void deleteCarrito(Context context, int idPersona) {
        String where = CarritoTable.ID_PERSONA + "=?";
        String[] whereArgs = {idPersona + ""};
        DBSource db = new DBSource(context);
        db.getWritableDatabase().delete(CarritoTable.TABLE_NAME, where, whereArgs);
    }

    /**
     * Metodo para borrar todos los registros del carro
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     */
    public void deleteCarritoProducto(Context context, int idPersona, int idProducto) {
        String where = CarritoTable.ID_PERSONA + "=? AND " + CarritoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {String.valueOf(idPersona), String.valueOf(idProducto)};
        DBSource db = new DBSource(context);
        db.getWritableDatabase().delete(CarritoTable.TABLE_NAME, where, whereArgs);
    }

    /**
     * Metodo para actualizar el carro por si hay una nueva cantidad
     *
     * @param context    el contexto de la actividad
     * @param idProducto el id del producto
     * @param idPersona  el id del usuario
     * @param cantidad   la cantidad del producto
     */
    public void updateCarrito(Context context, int idPersona, int idProducto, int cantidad) {
        String where = CarritoTable.ID_PERSONA + "=? AND " + CarritoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {String.valueOf(idPersona), String.valueOf(idProducto)};
        DBSource db = new DBSource(context);
        Carrito c = new Carrito(idPersona, idProducto, cantidad);
        db.getWritableDatabase().update(CarritoTable.TABLE_NAME, c.mapearAContenValues(), where, whereArgs);
    }

    /**
     * Metodo para comprobar sacar todos los carritos
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @return devuelve la lista de los carritos del usuario
     */
    public List<Carrito> findCarritoProducto(Context context, int idPersona) {
        String where = CarritoTable.ID_PERSONA + "=?";
        String[] whereArgs = {String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(CarritoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Carrito> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Carrito().loadCarritoFromCursor(cursor));
        }
        cursor.close();
        return lista;
    }

    /**
     * Metodo para obtener todos los productos en el carrito de un usuario
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @return devuelve la lista de los productos del carrito
     */
    public List<Producto> findAllCaritos(Context context, int idPersona) {
        List<Carrito> listaCarro = findCarritoProducto(context, idPersona);
        List<Producto> lista = new ArrayList<>();
        String where = ProductoTable.ID_PRODUCTO + "=?";
        DBSource db = new DBSource(context);
        for (Carrito c : listaCarro) {
            String[] whereArgs = {c.getIdProducto() + ""};
            Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
            while (cursor.moveToNext()) {
                lista.add(new Producto().loadProductoFromCursor(cursor));
            }
            cursor.close();
        }
        return lista;
    }
}


