package com.example.icroqueta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icroqueta.adapter.ProductRecyclerViewAdapter;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.dto.ProductoCarrito;

import java.util.List;
import java.util.Objects;

public class ShoppingCarActivity extends MenuBar {
    Intent intent;
    int idPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        cargarIdUsuario();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
        //Esto le envia al CroquetasRecyclerViewAdapter todos los productos de la base de datos
        DBHelper db = new DBHelper();
        List<ProductoCarrito> productos=db.findProductosInCarrito(this,idPersona);
        //Para visualizar el Recicle view en esta Vista
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(productos);
        RecyclerView croquetasRecyclerView = findViewById(R.id.carritoRecyclerView);
        croquetasRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        croquetasRecyclerView.setAdapter(adapter);
        actualizarTotal();

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    /**
     * Método para abrir la página de pago
     * @param view nuestra view
     */
    public void openPagar(View view) {
        //Comprobar los datos de registro si no los tiene se habre register
        DBHelper db = new DBHelper();
        if(db.totalProductosEnCarrito(this, idPersona)!=0) {
            intent = new Intent(this, RegisterPaymentActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"No tiene nada en su carrito",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para que se vaya actualizando el precio total
     */
    public void actualizarTotal(){
        DBHelper db = new DBHelper();
        //Aqui se mete el total de la cantidad por el precio de los productos
        TextView total=findViewById(R.id.carritoTotal);
        total.setText(String.format("%s€", db.totalProductosEnCarrito(this, idPersona)));
    }

    /**
     * Método para limpiar todos los elementos del carro
     */
    public void limpiarCarro(View view) {
        DBHelper db = new DBHelper();
        db.deleteCarrito(this,idPersona);
        refrescar();
    }
    /**
     * Método para refrescar la pantalla
     */
    public void refrescar(){
        finish();
        startActivity(getIntent());
    }

    /**
     * Método para sacar el id del usuario de las credenciales guardadas
     */
    private void cargarIdUsuario() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        idPersona = preferences.getInt("id", 0);
    }
}