package com.example.icroqueta.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.example.icroqueta.database.dto.ProductoCarrito;
import com.example.icroqueta.database.entidades.*;
import com.example.icroqueta.database.tablas.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    //****** Métodos tabla ProductosCarrito ******//

    /**
     * Este método recoge todos los productos que estén o no en un carrito
     * y los mete en una lista.
     *
     * @param context el contexto de la actividad
     * @return la lista de los productos.
     */
    public List<ProductoCarrito> allProductosCarrito(Context context, int idPersona) {
        List<ProductoCarrito> productos = allProducto(context); //Todos los productos
        List<ProductoCarrito> productosCarrito = findProductosInCarrito(context, idPersona);//Todos los productos que haya en el carrito
        for (ProductoCarrito p : productos) {
            for (ProductoCarrito c : productosCarrito) {
                if (c.getIdProducto().equals(p.getIdProducto())) { //Si son el mismo producto se actualiza con la cantidad del carro
                    p.setCantidad(c.getCantidad());
                }
            }
        }
        return productos;
    }

    /**
     * Este método recoge todos los productos que estén o no en un carrito
     * y los mete en una lista.
     *
     * @param context el contexto de la actividad
     * @return la lista de los productos.
     */
    public List<ProductoCarrito> allProductosCarritoById(Context context, int idPersona, List<String> idProducto) {
        List<ProductoCarrito> productos = allProductoById(context, idProducto); //Todos los productos
        List<ProductoCarrito> productosCarrito = findProductosInCarrito(context, idPersona);//Todos los productos que haya en el carrito
        for (ProductoCarrito p : productos) {
            for (ProductoCarrito c : productosCarrito) {
                if (c.getIdProducto().equals(p.getIdProducto())) { //Si son el mismo producto se actualiza con la cantidad del carro
                    p.setCantidad(c.getCantidad());
                }
            }
        }
        return productos;
    }


    /**
     * Este método recoge únicamente los productos que estan en el carrito
     * y los mete en una lista.
     *
     * @param context el contexto de la actividad
     * @return la lista de los productos de un carrito.
     */
    public List<ProductoCarrito> findProductosInCarrito(Context context, int idPersona) {
        DBSource db = new DBSource(context);
        String[] whereArgs = {String.valueOf(idPersona)};
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM " + ProductoTable.TABLE_NAME + " a INNER JOIN " + CarritoTable.TABLE_NAME + " b ON a." + ProductoTable.ID_PRODUCTO + "=b." + CarritoTable.ID_PRODUCTO + " WHERE b." + CarritoTable.ID_PERSONA + "=?", whereArgs);
        List<ProductoCarrito> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new ProductoCarrito().loadProductoCarritoFromCursor(cursor));
        }
        cursor.close();
        return lista;
    }

    /**
     * Método para calcular la suma del precio total,
     * para ello primero saca la cantidad y la multiplica por su precio.
     *
     * @param context el contexto de la actividad
     * @return la lista de los productos
     */
    public double totalProductosEnCarrito(Context context, int idPersona) {
        String query = "SELECT a.*,b." + CarritoTable.CANTIDAD + ",b." + CarritoTable.ID_PERSONA + " FROM " + ProductoTable.TABLE_NAME + " a LEFT JOIN " + CarritoTable.TABLE_NAME + " b ON a." + ProductoTable.ID_PRODUCTO + "=b." + CarritoTable.ID_PRODUCTO;
        DBSource db = new DBSource(context);
        double total = 0;
        double precio;
        int cantidad;
        Cursor cursor = db.getReadableDatabase().rawQuery(query, null);
        while (cursor.moveToNext()) {
            ProductoCarrito pc = new ProductoCarrito().loadProductoCarritoFromCursor(cursor);
            if (pc.getIdPersona() == idPersona || pc.getIdPersona() == 0) {
                precio = cursor.getDouble(cursor.getColumnIndex(ProductoTable.PRECIO_UD)); // sacamos el precio
                cantidad = cursor.getInt(cursor.getColumnIndex(CarritoTable.CANTIDAD)); // sacamos la cantidad
                total += (precio * cantidad); // lo calculamos al total
            }
        }
        return Math.floor(total * 100) / 100;
    }

    /**
     * Este método sirve para sacar un unico producto de la bd.
     *
     * @param context    el contexto de la actividad
     * @param idPersona  la id del usuario
     * @param idProducto la id del producto
     * @return un objeto producto
     */
    public ProductoCarrito getProductoCarrito(Context context, int idPersona, int idProducto) {
        List<ProductoCarrito> todos = allProductosCarrito(context, idPersona);
        for (ProductoCarrito pc : todos) {
            if (pc.getIdProducto() == idProducto) {
                return pc;
            }
        }
        return null;
    }

    /**
     * Método para obtener todos los productos de la bd.
     *
     * @param context el contexto de la actividad
     * @return La lista de productos de la bd
     */
    public List<ProductoCarrito> allProducto(Context context) {
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, null, null, null, null, null);
        List<ProductoCarrito> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new ProductoCarrito().loadProductoCarritoFromCursor(cursor));
        }
        return lista;
    }

    /**
     * Método para obtener todos los productos de la bd por la idProducto
     *
     * @param context el contexto de la actividad
     * @return La lista de productos de la bd
     */
    public List<ProductoCarrito> allProductoById(Context context, List<String> idProducto) {
        StringBuilder where = new StringBuilder(ProductoTable.ID_PRODUCTO + "=?");
        String[] whereArgs = new String[idProducto.size()];
        whereArgs[0] = idProducto.get(0);

        for (int i = 0; i < idProducto.size() - 1; i++) {
            where.append(" OR " + ProductoTable.ID_PRODUCTO + "=?");
            whereArgs[i + 1] = idProducto.get(i + 1);
        }
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, where.toString(), whereArgs, null, null, null);
        List<ProductoCarrito> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new ProductoCarrito().loadProductoCarritoFromCursor(cursor));
        }
        return lista;
    }

    /**
     * Método para obtener solo un producto por su id
     *
     * @param context el contexto de la actividad
     * @return La lista de productos de la bd
     */
    public Producto oneProducto(Context context, int idProducto) {
        DBSource db = new DBSource(context);
        String where = ProductoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {idProducto + ""};

        Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Producto> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Producto().loadProductoFromCursor(cursor));
        }
        return lista.get(0);
    }

    /**
     * Método para comprobar si existe un determinado producto en el carrito.
     *
     * @param context    el contexto de la actividad
     * @param idProducto el id del producto
     * @param idPersona  el id del usuario
     * @return true si existe, false si no existe en la bd
     */
    public boolean notExistCarritoProducto(Context context, int idPersona, int idProducto) {
        String where = CarritoTable.ID_PERSONA + "=? AND " + CarritoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {idPersona + "", idProducto + ""};
        DBSource db = new DBSource(context);
        @SuppressLint("Recycle") Cursor cursor = db.getReadableDatabase().query(CarritoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        return cursor.getCount() == 0;
    }

    /**
     * Método para borrar solo un producto del carro.
     *
     * @param context    el contexto de la actividad
     * @param idPersona  el id del usuario
     * @param idProducto el id del producto que se quiera borrar
     */
    public void deleteCarritoProducto(Context context, int idPersona, int idProducto) {
        String where = CarritoTable.ID_PERSONA + "=? AND " + CarritoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {String.valueOf(idPersona), String.valueOf(idProducto)};
        DBSource db = new DBSource(context);
        db.getWritableDatabase().delete(CarritoTable.TABLE_NAME, where, whereArgs);
    }


    //****** Métodos tabla Carrito ******//

    /**
     * Método para obtener todos los productos del carrito de una persona.
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id de la persona
     * @return La lista de productos de la bd
     */
    public List<Carrito> allCarritoPersona(Context context, int idPersona) {
        String where = CarritoTable.ID_PERSONA + "=?";
        String[] whereArgs = {String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(CarritoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Carrito> carrito = new ArrayList<>();
        while (cursor.moveToNext()) {
            carrito.add(new Carrito().loadCarritoFromCursor(cursor));
        }
        return carrito;
    }

    /**
     * Método para añadir un producto al carrito para guardarlo cuando cierre la app
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
     * Método para actualizar el carro por si hay una nueva cantidad
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
     * Método para borrar todos los registros del carro
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


    //****** Métodos tabla Producto ******//

    /**
     * Método para obtener un producto de la bd por su id.
     *
     * @param context el contexto de la actividad
     * @return un producto
     */
    public Producto findProducto(Context context, int idProducto) {
        String where = ProductoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {String.valueOf(idProducto)};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(ProductoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Producto> productos = new ArrayList<>();
        while (cursor.moveToNext()) {
            productos.add(new Producto().loadProductoFromCursor(cursor));
        }
        return productos.get(0);
    }

    //****** Métodos tabla Persona ******//

    /**
     * Método para añadir un registro nuevo a la base de datos Persona.
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
        long resultado = db.getWritableDatabase().insert(PersonaTable.TABLE_NAME, null, usuario.mapearAContenValues());
        return resultado != -1;
    }

    /**
     * Método para borrar todos los datos de una persona.
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     */
    public void deletePersona(Context context, int idPersona) {
        String where = PersonaTable.ID_PERSONA + "=?";
        String[] whereArgs = {idPersona + ""};
        DBSource db = new DBSource(context);
        db.getWritableDatabase().delete(PersonaTable.TABLE_NAME, where, whereArgs);
    }

    /**
     * Método para el loggin de la aplicacion que comprueba si existe ese correo
     * y si existe, si coincide con la contraseña.
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
            return 0;//No se encuentra el correo
        }
        for (Persona p : lista) {
            if (p.getContrasenya().equals(pass)) {
                return p.getIdPersona(); //Si encuentra el usuario y coinciden las contraseñas nos devuelve la id
            }
        }
        return -1; //No coincide la contraseña
    }

    /**
     * Método para encontrar a un usuario por su id.
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
     * Método para comprobar si existe otro usuario con el mismo correo
     *
     * @param context   el contexto de la actividad
     * @param correo    nombre que se quiere comprobar
     * @param idPersona la id de la persona que solicita la comprobación
     * @return true si existe, false si no existe en la bd
     */
    public boolean notExistCorreo(Context context, int idPersona, String correo) {
        String where = PersonaTable.CORREO + "=? AND " + PersonaTable.ID_PERSONA + "<>?";
        String[] whereArgs = {correo, String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        @SuppressLint("Recycle") Cursor cursor = db.getReadableDatabase().query(PersonaTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        return cursor.getCount() == 0;
    }

    /**
     * Método para actualizar el nombre de la persona
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @param nombre    el nuevo nombre
     */
    public void updateNombrePersona(Context context, int idPersona, String nombre) {
        String where = PersonaTable.ID_PERSONA + "=?";
        String[] whereArgs = {String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        Persona p = findPersonaId(context, idPersona);
        p.setNombre(nombre);
        db.getWritableDatabase().update(PersonaTable.TABLE_NAME, p.mapearAContenValues(), where, whereArgs);
    }

    /**
     * Método para actualizar el apellido de la persona
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @param apellido  el nuevo apellido
     */
    public void updateApellidoPersona(Context context, int idPersona, String apellido) {
        String where = PersonaTable.ID_PERSONA + "=?";
        String[] whereArgs = {String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        Persona p = findPersonaId(context, idPersona);
        p.setApellidos(apellido);
        db.getWritableDatabase().update(PersonaTable.TABLE_NAME, p.mapearAContenValues(), where, whereArgs);
    }

    /**
     * Método para actualizar el nif de la persona
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @param nif       el nuevo nif
     */
    public void updateNifPersona(Context context, int idPersona, String nif) {
        String where = PersonaTable.ID_PERSONA + "=?";
        String[] whereArgs = {String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        Persona p = findPersonaId(context, idPersona);
        p.setNif(nif);
        db.getWritableDatabase().update(PersonaTable.TABLE_NAME, p.mapearAContenValues(), where, whereArgs);
    }

    /**
     * Método para actualizar el correo de la persona
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @param correo    el nuevo correo
     */
    public void updateCorreoPersona(Context context, int idPersona, String correo) {
        String where = PersonaTable.ID_PERSONA + "=?";
        String[] whereArgs = {String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        Persona p = findPersonaId(context, idPersona);
        p.setCorreo(correo);
        db.getWritableDatabase().update(PersonaTable.TABLE_NAME, p.mapearAContenValues(), where, whereArgs);
    }

    /**
     * Método para actualizar la contraseña de la persona
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @param pass      la nueva contraseña
     */
    public void updateContrasenyaPersona(Context context, int idPersona, String pass) {
        //todo hash contraseña
        String where = PersonaTable.ID_PERSONA + "=?";
        String[] whereArgs = {String.valueOf(idPersona)};
        DBSource db = new DBSource(context);
        Persona p = findPersonaId(context, idPersona);
        p.setContrasenya(pass);
        db.getWritableDatabase().update(PersonaTable.TABLE_NAME, p.mapearAContenValues(), where, whereArgs);
    }

    //todo hash tarjeta


    //****** Métodos tabla Linea ******//

    /**
     * Método para volcar todas los productos que había en el carrito en lineas del pedido.
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @param idPedido  el id del pedido
     */
    public void addLinea(Context context, int idPersona, int idPedido) {
        DBSource db = new DBSource(context);
        //Ahora saca todos los productos del carrito y los pasa a Lista
        //todo actualizar stock del pedido

        List<ProductoCarrito> lista = findProductosInCarrito(context, idPersona);
        for (ProductoCarrito pc : lista) {
            updateProducto(context, pc.getIdProducto(), pc.getCantidad());
            Linea linea = new Linea(idPedido, pc.getIdProducto(), pc.getCantidad());
            db.getWritableDatabase().insert(LineaTable.TABLE_NAME, null, linea.mapearAContenValues());
        }
        deleteCarrito(context, idPersona);
    }

    /**
     * Método para calcular cuantas lineas tiene un pedido.
     *
     * @param context  el contexto de la actividad
     * @param idPedido la id del pedido
     * @return la lista de los productos
     */
    public List<Linea> allLineasProducto(Context context, int idPedido) {
        String where = LineaTable.ID_PEDIDO + "=? ";
        String[] whereArgs = {String.valueOf(idPedido)};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(LineaTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Linea> lineas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Linea l = new Linea().loadLineaFromCursor(cursor);
            lineas.add(l);
        }
        return lineas;
    }


    //****** Métodos tabla Pedido ******//

    /**
     * Método para añadir un registro a la base de datos Pedido
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @return true si se ha añadido bien y false si ha habido un problema
     */
    public boolean addPedido(Context context, int idPersona) {
        DBSource db = new DBSource(context);
        //Esto es para añadir el pedido con la fecha actual
        long date = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaPedido = sdf.format(date);
        Pedido usuario = new Pedido(idPersona, fechaPedido, "Activo", totalProductosEnCarrito(context, idPersona));
        long resultado = db.getWritableDatabase().insert(PedidoTable.TABLE_NAME, null, usuario.mapearAContenValues());
        //Aqui sacamos todos los pedidos y cogemos el último
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT  * FROM " + PedidoTable.TABLE_NAME, null);
        if (cursor.moveToLast()) {
            Pedido pc = new Pedido().loadPedidoaFromCursor(cursor);
            if (pc.getIdPersona() == idPersona || pc.getIdPersona() == 0) {
                addLinea(context, idPersona, pc.getIdPedido());
            }
        }
        return resultado != -1;
    }


    /**
     * Método sacar una lista de los pedidos en la bd.
     *
     * @param context el contexto de la actividad
     * @return Una lista de todos los pedidos
     */
    public List<Pedido> allPedidos(Context context) {
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(PedidoTable.TABLE_NAME, null, null, null, null, null, null);
        List<Pedido> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Pedido().loadPedidoaFromCursor(cursor));
        }
        return lista;
    }

    /**
     * Método sacar una lista de los pedidos activos.
     *
     * @param context el contexto de la actividad
     * @return Una lista de todos los pedidos
     */
    public List<Pedido> allPedidosActivos(Context context) {
        List<Pedido> allPedidos = allPedidos(context);
        List<Pedido> pedidosActivos = new ArrayList<>();
        for (Pedido pd : allPedidos) {
            if (pd.getEstado().equals("Activo")) {
                pedidosActivos.add(pd);
            }
        }
        return pedidosActivos;
    }


    /**
     * Método sacar la lista de pedidos CANCELADOS y ENTREGADOS que tiene un usuario.
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id de la persona
     * @return Una lista de todos los pedidos
     */
    public List<Pedido> allPedidosNoActivosUsuario(Context context, int idPersona) {
        List<Pedido> allPedidos = allPedidos(context);
        List<Pedido> pedidosCancelados = new ArrayList<>();
        for (Pedido pd : allPedidos) {
            if ((pd.getEstado().equals("Cancelado") || pd.getEstado().equals("Entregado")) && idPersona == pd.getIdPersona()) {
                pedidosCancelados.add(pd);
            }
        }
        return pedidosCancelados;
    }

    /**
     * Método sacar la lista de pedidos ACTIVOS que tiene un usuario.
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id de la persona
     * @return Una lista de todos los pedidos
     */
    public List<Pedido> allPedidosActivosUsuario(Context context, int idPersona) {
        List<Pedido> allPedidosActivos = allPedidosActivos(context);
        List<Pedido> pedidosActivos = new ArrayList<>();
        for (Pedido pd : allPedidosActivos) {
            if (idPersona == pd.getIdPersona()) {
                pedidosActivos.add(pd);
            }
        }
        return pedidosActivos;
    }

    /**
     * Método para actualizar el estado del pedido.
     *
     * @param context   el contexto de la actividad
     * @param estado    el estado del producto
     * @param idPersona el id del usuario
     * @param idPedido  el id del pedido
     */
    public void updatePedido(Context context, Integer idPedido, Integer idPersona, String fechaPedido, String estado, double importe) {
        String where = PedidoTable.ID_PERSONA + "=? AND " + PedidoTable.ID_PEDIDO + "=?";
        String[] whereArgs = {String.valueOf(idPersona), String.valueOf(idPedido)};
        DBSource db = new DBSource(context);
        Pedido p = new Pedido(idPedido, idPersona, fechaPedido, estado, importe);
        db.getWritableDatabase().update(PedidoTable.TABLE_NAME, p.mapearAContenValues(), where, whereArgs);
    }

    //****** Métodos tabla Ingrediente ******//

    /**
     * Este método recoge todos los ingredientes de la base de datos
     * y los mete en una lista.
     *
     * @param context el contexto de la actividad
     * @return la lista de los ingredientes.
     */
    public List<Ingrediente> allIngredientes(Context context) {
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(IngredienteTable.TABLE_NAME, null, null, null, null, null, null);
        List<Ingrediente> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Ingrediente().loadIngredienteFromCursor(cursor));
        }
        return lista;
    }

    /**
     * Este método recoge todos los ingredientes de la base de datos
     * y los mete en una lista.
     *
     * @param context el contexto de la actividad
     * @return la lista de los ingredientes.
     */
    public List<TipoIngrediente> allTipoIngredientes(Context context) {
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(TipoIngredienteTable.TABLE_NAME, null, null, null, null, null, null);
        List<TipoIngrediente> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new TipoIngrediente().loadTipoIngredienteFromCursor(cursor));
        }
        return lista;
    }


    /**
     * Método para sacar solo la lista de ingredientes con el mismo tipo
     *
     * @param context el contexto de la actividad
     * @param idtipo  el idtipo de ingrediente
     * @return la lista de los ingredientes
     */
    public List<TipoIngrediente> TiposDeIngredientesDeTipo(Context context, int idtipo) {
        String where = TipoIngredienteTable.ID_TIPO + "=? ";
        String[] whereArgs = {String.valueOf(idtipo)};
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(TipoIngredienteTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<TipoIngrediente> tipos = new ArrayList<>();
        while (cursor.moveToNext()) {
            TipoIngrediente l = new TipoIngrediente().loadTipoIngredienteFromCursor(cursor);
            tipos.add(l);
        }
        return tipos;
    }

    /**
     * Método para sacar solo la lista de los ingredientes con un mismo id
     *
     * @param context          el contexto de la actividad
     * @param tipoIngredientes las id de los ingredientes
     * @return la lista de los ingredientes
     */
    public List<Ingrediente> allIngredientesDeUnTipo(Context context, List<TipoIngrediente> tipoIngredientes) {
        String where = IngredienteTable.ID_INGREDIENTE + "=?";

        String[] whereArgs = new String[tipoIngredientes.size()];
        whereArgs[0] = String.valueOf(tipoIngredientes.get(0));

        for (int i = 0; i < tipoIngredientes.size() - 1; i++) {
            where += " OR " + IngredienteTable.ID_INGREDIENTE + "=?";
            whereArgs[i + 1] = String.valueOf(tipoIngredientes.get(i + 1));
        }
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(IngredienteTable.TABLE_NAME, null, where, whereArgs, null, null, null, null);
        List<Ingrediente> ingredientes = new ArrayList<>();

        while (cursor.moveToNext()) {
            Ingrediente l = new Ingrediente().loadIngredienteFromCursor(cursor);
            ingredientes.add(l);
        }
        return ingredientes;
    }

    /**
     * Método para sacar solo la lista de los identificadores del producto, que tengan los id que introduzcamos los id de los ingredientes
     *
     * @param context        el contexto de la actividad
     * @param idIngredientes las id de los ingredientes
     * @return la lista de los ingredientes
     */
    public List<String> idProductosIdIngredientes(Context context, List<String> idIngredientes) {
        StringBuilder where = new StringBuilder(IngredienteProductoTable.ID_INGREDIENTE + "=?");

        String[] whereArgs = new String[idIngredientes.size()];
        whereArgs[0] = idIngredientes.get(0);

        for (int i = 0; i < idIngredientes.size() - 1; i++) {
            where.append(" OR " + IngredienteProductoTable.ID_INGREDIENTE + "=?");
            whereArgs[i + 1] = idIngredientes.get(i + 1);
        }
        DBSource db = new DBSource(context);
        Cursor cursor = db.getReadableDatabase().query(IngredienteProductoTable.TABLE_NAME, null, where.toString(), whereArgs, null, null, null, null);
        List<String> idProductos = new ArrayList<>();

        while (cursor.moveToNext()) {
            IngredienteProducto l = new IngredienteProducto().loadIngredienteProductoFromCursor(cursor);
            idProductos.add(String.valueOf(l.getIdProducto()));
        }

        return idProductos;
    }

    /**
     * Método para actualizar la cantidad del stock de un producto.
     *
     * @param context    el contexto de la actividad
     * @param idProducto el id del producto
     * @param cantidad   la cantidad que se va a restar al stock
     */
    public void updateProducto(Context context, Integer idProducto, Integer cantidad) {
        String where = ProductoTable.ID_PRODUCTO + "=?";
        String[] whereArgs = {String.valueOf(idProducto)};
        DBSource db = new DBSource(context);
        Producto p = oneProducto(context, idProducto);
        p.setStock(p.getStock() - cantidad);
        db.getWritableDatabase().update(ProductoTable.TABLE_NAME, p.mapearAContenValues(), where, whereArgs);
    }

    /**
     * Método para obtener solo un producto por su id
     *
     * @param context el contexto de la actividad
     * @return La lista de productos de la bd
     */
    public Tipo oneTipo(Context context, int idTipo) {
        DBSource db = new DBSource(context);
        String where = TipoTable.ID_TIPO + "=?";
        String[] whereArgs = {String.valueOf(idTipo)};

        Cursor cursor = db.getReadableDatabase().query(TipoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Tipo> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Tipo().loadTipoFromCursor(cursor));
        }
        return lista.get(0);
    }

    /**
     * Método para obtener solo un producto por su id
     *
     * @param context el contexto de la actividad
     * @return La lista de productos de la bd
     */
    public Ingrediente oneIngrediente(Context context, int idIngrediente) {
        DBSource db = new DBSource(context);
        String where = IngredienteTable.ID_INGREDIENTE + "=?";
        String[] whereArgs = {String.valueOf(idIngrediente)};
        Cursor cursor = db.getReadableDatabase().query(IngredienteTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Ingrediente> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            lista.add(new Ingrediente().loadIngredienteFromCursor(cursor));
        }
        return lista.get(0);
    }

    //****** Métodos tabla PersonaTelefono y Telefono ******//

    /**
     * Método para añadir un producto al carrito para guardarlo cuando cierre la app
     *
     * @param context   el contexto de la actividad
     * @param idPersona el id del usuario
     * @param numero    el numero de telefono
     */
    //return id del telefono ultimo que añade el usuario
    public void addPersonaTelefono(Context context, int idPersona, int numero) {
        //primero comprobamos si el telefono está en la bd
        DBSource db = new DBSource(context);
        String where = TelefonoTable.NUMERO + "=?";
        String[] whereArgs = {String.valueOf(numero)};
        Cursor cursor = db.getReadableDatabase().query(TelefonoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Telefono> lista = new ArrayList<>();
        Telefono tlf;
        //En el caso de que no esté lo metemos
        if (cursor.getCount() == 0) {
            tlf = new Telefono(numero);
            db.getWritableDatabase().insert(TelefonoTable.TABLE_NAME, null, tlf.mapearAContenValues());
            //recogemos la id autogenerada para poder unirlo con el usuario
            cursor = db.getReadableDatabase().query(TelefonoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        }
        while (cursor.moveToNext()) {
            lista.add(new Telefono().loadTelefonoFromCursor(cursor));
        }
        tlf = lista.get(0);
        PersonaTelefono ptlf = new PersonaTelefono(idPersona, tlf.getIdTelefono());
        db.getWritableDatabase().insert(PersonaTelefonoTable.TABLE_NAME, null, ptlf.mapearAContenValues());
    }

    /**
     * Método para obtener solo un producto por su id
     *
     * @param context el contexto de la actividad
     * @return La lista de productos de la bd
     */
    public String oneTelefono(Context context, int idPersona) {
        try {
            DBSource db = new DBSource(context);
            String where = PersonaTelefonoTable.ID_PERSONA + "=?";
            String[] whereArgs = {String.valueOf(idPersona)};
            Cursor cursor = db.getReadableDatabase().query(PersonaTelefonoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
            List<PersonaTelefono> lista = new ArrayList<>();
            while (cursor.moveToNext()) {
                lista.add(new PersonaTelefono().loadPersonaTelefonoFromCursor(cursor));
            }
            PersonaTelefono ptlf = lista.get(0);
            where = TelefonoTable.ID_TELEFONO + "=?";
            whereArgs = new String[]{String.valueOf(ptlf.getIdTelefono())};
            cursor = db.getReadableDatabase().query(TelefonoTable.TABLE_NAME, null, where, whereArgs, null, null, null);
            List<Telefono> listat = new ArrayList<>();
            Telefono tlf;
            while (cursor.moveToNext()) {
                listat.add(new Telefono().loadTelefonoFromCursor(cursor));
            }
            return listat.get(0).getNumero() + "";
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }


    //****** Métodos tabla PersonaDireccion y Direccion ******//

    /**
     * Método para añadir un producto al carrito para guardarlo cuando cierre la app
     *
     * @param context el contexto de la actividad
     * @param idPersona la id del usuario
     * @param calle la calle de la direccion
     * @param portal el portal de la direccion
     * @param puerta la puerta de la direccion
     * @param codigo el codigo de la direccion
     * @param localidad la localidad de la direccion
     * @param coordenadas las coordenadas de la direccion
     */
    public void addPersonaDireccion(Context context, int idPersona, String calle, String portal, String puerta, String codigo, String localidad, String coordenadas) {
        //primero comprobamos si el telefono está en la bd
        DBSource db = new DBSource(context);
        String where = DireccionTable.CALLE + "=? AND " + DireccionTable.PORTAL + "=? AND " + DireccionTable.PUERTA + "=? AND " + DireccionTable.CODIGO_POSTAL + "=? AND " + DireccionTable.LOCALIDAD + "=? AND " + DireccionTable.COORDENADA + "=?";
        String[] whereArgs = {calle, portal, puerta, codigo, localidad, coordenadas};
        Cursor cursor = db.getReadableDatabase().query(DireccionTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Direccion> lista = new ArrayList<>();
        Direccion dir;
        //En el caso de que no esté lo metemos
        if (cursor.getCount() == 0) {
            dir = new Direccion(calle, portal, puerta, codigo, localidad, coordenadas);
            db.getWritableDatabase().insert(DireccionTable.TABLE_NAME, null, dir.mapearAContenValues());
            //recogemos la id autogenerada para poder unirlo con el
            cursor = db.getReadableDatabase().query(DireccionTable.TABLE_NAME, null, where, whereArgs, null, null, null);
        }
        while (cursor.moveToNext()) {
            lista.add(new Direccion().loadDireccionFromCursor(cursor));
        }
        dir = lista.get(0);
        PersonaDireccion pdir = new PersonaDireccion(idPersona, dir.getIdDireccion());
        db.getWritableDatabase().insert(PersonaTelefonoTable.TABLE_NAME, null, pdir.mapearAContenValues());
    }

    /**
     * Método para obtener solo un producto por su id
     *
     * @param context el contexto de la actividad
     * @return La lista de productos de la bd
     */
    public Direccion oneDireccion(Context context, int idPersona) {
        try {
            DBSource db = new DBSource(context);
            String where = PersonaDireccionTable.ID_PERSONA + "=?";
            String[] whereArgs = {String.valueOf(idPersona)};
            Cursor cursor = db.getReadableDatabase().query(PersonaDireccionTable.TABLE_NAME, null, where, whereArgs, null, null, null);
            List<PersonaDireccion> lista = new ArrayList<>();
            while (cursor.moveToNext()) {
                lista.add(new PersonaDireccion().loadPersonaDireccionFromCursor(cursor));
            }
            PersonaDireccion pdir = lista.get(0);
            where = TelefonoTable.ID_TELEFONO + "=?";
            whereArgs = new String[]{String.valueOf(pdir.getIdDireccion())};
            cursor = db.getReadableDatabase().query(DireccionTable.TABLE_NAME, null, where, whereArgs, null, null, null);
            List<Direccion> listat = new ArrayList<>();
            Direccion dir;
            while (cursor.moveToNext()) {
                listat.add(new Direccion().loadDireccionFromCursor(cursor));
            }
            return listat.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}

