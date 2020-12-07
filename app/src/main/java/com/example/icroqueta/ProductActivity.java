package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.dto.ProductoCarrito;

import java.util.Objects;

public class ProductActivity extends MenuBar {

    private int id_producto;
    private  TextView nombre;
    private  TextView precio;
    private  TextView descripcion;
    private TextView cantidad;
    public ImageView foto;
 private ProductoCarrito producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        cantidad = findViewById(R.id.producto_cantidad_row);

        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        id_producto = extras.getInt("ID_PRODUCTO");
        DBHelper db = new DBHelper();

        //Recogemos el producto
        nombre = findViewById(R.id.producto_nombre_row);
        precio = findViewById(R.id.producto_precio_row);
        descripcion = findViewById(R.id.producto_descripcion_row);
        cantidad = findViewById(R.id.producto_cantidad_row);
        foto = findViewById(R.id.producto_imagen);

        producto= db.getProductoCarrito(this,LoginActivity.usuario.getIdPersona(),id_producto);
        if(producto!=null){
            //Cargamos los datos del producto y comprobamos si está en el carrito
            Glide.with(this)
                    .load(producto.getImagen())
                    .into(foto);
            nombre.setText(producto.getNombre());
            descripcion.setText(producto.getDescripcion());
            precio.setText(String.format("%s€/ud", producto.getPrecioUd()));
            cantidad.setText(String.valueOf(producto.getCantidad()));
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
            db.deleteCarritoProducto(this, LoginActivity.usuario.getIdPersona(), producto.getIdProducto());
        } else if (aux > 1) {
            cantidad.setText(String.valueOf(aux - 1));
            //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
            if (db.notExistCarritoProducto(this, LoginActivity.usuario.getIdPersona(), producto.getIdProducto())) {
                db.addCarrito(this,  LoginActivity.usuario.getIdPersona(), producto.getIdProducto(),Integer.parseInt(cantidad.getText().toString()));
            } else {
                db.updateCarrito(this, LoginActivity.usuario.getIdPersona(),producto.getIdProducto(),  Integer.parseInt(cantidad.getText().toString()));
            }
        }
    }
    /**
     * Método para sumar la cantidad de producto con el botón sumar
     * @param view nuestra view
     */
    public void sumarCantidad(View view) {
        DBHelper db = new DBHelper();
        //Obtiene el valor del TextView
        String valor = cantidad.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        cantidad.setText(String.valueOf(aux + 1));
        //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
        if (db.notExistCarritoProducto(this, LoginActivity.usuario.getIdPersona(), producto.getIdProducto())) {
            db.addCarrito(this, LoginActivity.usuario.getIdPersona(),  producto.getIdProducto(),Integer.parseInt(cantidad.getText().toString()));
        } else {
            db.updateCarrito(this, LoginActivity.usuario.getIdPersona(), producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
        }
    }

    /**
     * Método que nos abre el carrito
     * @param view nuestra view
     */
    public void FinalizarCarrito(View view) {
        Intent  intent = new Intent(this, ShoppingCarActivity.class);
        startActivity(intent);
    }
}