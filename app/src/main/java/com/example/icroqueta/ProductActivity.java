package com.example.icroqueta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.dto.ProductoCarrito;

import java.util.Objects;

public class ProductActivity extends MenuBar {

    private TextView cantidad;
    public ImageView foto;
    private ProductoCarrito producto;
    int idPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        cantidad = findViewById(R.id.producto_cantidad_row);
        cargarIdUsuario();
        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        int id_producto = extras.getInt("ID_PRODUCTO");
        DBHelper db = new DBHelper();


        //Recogemos el producto
        TextView nombre = findViewById(R.id.producto_nombre_detalle);
        TextView precio = findViewById(R.id.producto_precio_detalle);
        TextView descripcion = findViewById(R.id.producto_descripcion_detalle);
        cantidad = findViewById(R.id.producto_cantidad_detalle);
        foto = findViewById(R.id.producto_imagen_detalle);
        TextView stock = findViewById(R.id.producto_stock_detalle);

        producto = db.getProductoCarrito(this, idPersona, id_producto);
        if (producto != null) {
            //Cargamos los datos del producto y comprobamos si está en el carrito
            Glide.with(this)
                    .load(producto.getImagen())
                    .into(foto);
            nombre.setText(producto.getNombre());
            descripcion.setText(producto.getDescripcion());
            precio.setText(String.format("%s€/ud", producto.getPrecioUd()));
            cantidad.setText(String.valueOf(producto.getCantidad()));
            stock.setText(stock.getResources().getQuantityString(R.plurals.disponibilidad, producto.getStock(), producto.getStock()));
        }

        //Esto es por si no tuviese stock el producto
        assert producto != null;
        if (db.oneProducto(this, producto.getIdProducto()).getStock() == 0) {
            this.findViewById(R.id.btn_menos_detalle).setVisibility(View.GONE);
            this.findViewById(R.id.btn_mas_detalle).setVisibility(View.GONE);
            this.findViewById(R.id.producto_cantidad_detalle).setVisibility(View.GONE);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    /**
     * Método para restar la cantidad de producto con el botón restar
     *
     * @param view nuestra view
     */
    public void restarCantidad(View view) {
        DBHelper db = new DBHelper();
        //Obtiene el valor del TextView
        String valor = cantidad.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        //Se define el valor de una resta y en el caso de que el valor sea igual a 0, se mantiene
        if (aux == 1) {
            cantidad.setText("0");
            //Si tuviesemos este elemento en el carrito, entonces lo borramos
            db.deleteCarritoProducto(this, idPersona, producto.getIdProducto());
        } else if (aux > 1) {
            cantidad.setText(String.valueOf(aux - 1));
            //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
            if (db.notExistCarritoProducto(this, idPersona, producto.getIdProducto())) {
                db.addCarrito(this, idPersona, producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
            } else {
                db.updateCarrito(this, idPersona, producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
            }
        }
    }

    /**
     * Método para sumar la cantidad de producto con el botón sumar
     *
     * @param view nuestra view
     */
    public void sumarCantidad(View view) {
        DBHelper db = new DBHelper();
        //Obtiene el valor del TextView
        String valor = cantidad.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        if (aux + 1 <= producto.getStock()) {
            cantidad.setText(String.valueOf(aux + 1));
            //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
            if (db.notExistCarritoProducto(this, idPersona, producto.getIdProducto())) {
                db.addCarrito(this, idPersona, producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
            } else {
                db.updateCarrito(this, idPersona, producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
            }
        } else {
            Toast.makeText(this, R.string.noPuedeAgregar, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método que nos abre el carrito
     *
     * @param view nuestra view
     */
    public void FinalizarCarrito(View view) {
        Intent intent = new Intent(this, ShoppingCarActivity.class);
        startActivity(intent);
    }

    /**
     * Método para sacar el id del usuario de las credenciales guardadas
     */
    private void cargarIdUsuario() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        idPersona = preferences.getInt("id", 0);
    }
}