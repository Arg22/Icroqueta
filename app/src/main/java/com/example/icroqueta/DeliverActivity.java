package com.example.icroqueta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Pedido;

import java.util.List;
import java.util.Objects;

public class DeliverActivity extends MenuBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);

        //todo: meter mapa google


        //Esto le envia al ActiveRecyclerViewAdapter todos pedidos activos
        DBHelper db = new DBHelper();
        List<Pedido> pedidos=db.allPedidosActivos(this);

        if(pedidos.size()==0){
            Toast.makeText(this, "No tienes pedidos activos", Toast.LENGTH_SHORT).show();
        }

        //Para visualizar el Recicle view en esta Vista
        PedidosRecyclerViewAdapter adapter = new PedidosRecyclerViewAdapter(pedidos);
        RecyclerView activeRecyclerView = findViewById(R.id.deliverRecyclerView);
        activeRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        activeRecyclerView.setAdapter(adapter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home


    }

    @Override
    public boolean onSupportNavigateUp() {//Accion botón
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    public void refrescar() {
        finish();
        startActivity(getIntent());
    }
}