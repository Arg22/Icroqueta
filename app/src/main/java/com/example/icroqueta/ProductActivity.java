package com.example.icroqueta;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ProductActivity extends MenuBar {
    private TextView cantidad;
    private int id_producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        cantidad = findViewById(R.id.producto_cantidad_row);

        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        id_producto = extras.getInt("ID_PRODUCTO");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
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