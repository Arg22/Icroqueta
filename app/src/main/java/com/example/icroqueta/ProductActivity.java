package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Producto;

import java.util.List;
import java.util.Objects;

public class ProductActivity extends MenuBar {

    private int id_producto;
    private  TextView nombre;
    private  TextView precio;
    private  TextView descripcion;
    private TextView cantidad;
    public ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        cantidad = findViewById(R.id.producto_cantidad_row);

        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        id_producto = extras.getInt("ID_PRODUCTO");

        //Recogemos el producto
        DBHelper mp = new DBHelper();
        Producto producto =mp.findProducto(this,id_producto);
        nombre = findViewById(R.id.producto_nombre_row);
        precio = findViewById(R.id.producto_precio_row);
        descripcion = findViewById(R.id.producto_descripcion_row);
        cantidad = findViewById(R.id.producto_cantidad_row);        //todo enviar linea para mirar la cantidad
        foto = findViewById(R.id.producto_imagen);

        //Cargamos los datos del producto y comprobamos si está en el carrito
        Glide.with(this)
                .load(producto.getImagen())
                .into(foto);
        nombre.setText(producto.getNombre());
        descripcion.setText(producto.getDescripcion());
        precio.setText(String.format("%s€/ud", producto.getPrecioUd()));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }


    public void restarCantidad(View view) {
        //Obtiene el valor del TextView
        String valor = cantidad.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        //Se define el valor de una resta y en el caso de que el valor sea igual a 0, se mantiene
        if (aux == 0) {
            cantidad.setText("0");
        } else {
            cantidad.setText(String.valueOf(aux - 1));
        }
    }

    public void sumarCantidad(View view) {
        //Obtiene el valor del TextView
        String valor = cantidad.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        cantidad.setText(String.valueOf(aux + 1));
    }

    public void agregarCarrito(View view) {
        //todo:mandar id croquetas a shoppingCart y cantidad
        //Aqui se pasaria a la bd la cantidad
        //y el precio * cantidad
    }
}