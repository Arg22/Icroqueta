package com.example.icroqueta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.DTO.ProductoCarrito;

import java.util.List;
import java.util.Objects;

public class ShoppingCarActivity extends MenuBar {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shopping_car);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home


        //Esto le envia al CroquetasRecyclerViewAdapter todos los productos de la base de datos
        DBHelper db = new DBHelper();
        List<ProductoCarrito> productos=db.findProductosEnCarrito(this,LoginActivity.usuario.getIdPersona());
        //Para visualizar el Recicle view en esta Vista
        CroquetasRecyclerViewAdapter adapter = new CroquetasRecyclerViewAdapter(productos);
        RecyclerView croquetasRecyclerView = findViewById(R.id.carritoRecyclerView);
        croquetasRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        croquetasRecyclerView.setAdapter(adapter);

        //todo Futuro - Que se actualice al pulsar en el recycleView
        //Aqui se mete el total de la cantidad por el precio de los productos
        TextView total=findViewById(R.id.carritoTotal);
        total.setText(db.sumProductosEnCarrito(this, LoginActivity.usuario.getIdPersona()) +"€");
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    public void openPagar(View view) {
        //Comprobar los datos de registro si no los tiene se habre register
          intent= new Intent(this, RegisterPaymentActivity.class);
        startActivity(intent);
    }

}