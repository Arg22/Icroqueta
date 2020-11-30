package com.example.icroqueta;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class ProductActivity extends MenuBar {
    private TextView cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        cantidad = findViewById(R.id.producto_cantidad_row);
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
        if (aux == 0){
            cantidad.setText(""+0);
        }else {
            cantidad.setText("" + (aux - 1));
        }
    }

    public void sumarCantidad(View view) {
        //Obtiene el valor del TextView
        String valor = cantidad.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        cantidad.setText(""+(aux+1));
    }

    public void agregarCarrito(View view) {

        //Aqui se pasaria a la bd la cantidad
        //y el precio * cantidad
    }
}