package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Linea;

import java.util.List;
import java.util.Objects;

public class LineaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea);
        //Esto le envia al ActiveRecyclerViewAdapter todos pedidos activos

        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        int id_pedido = extras.getInt("ID_PEDIDO");


        DBHelper db = new DBHelper();
        List<Linea> lineas = db.allLineasProducto(this, id_pedido);

        //Para visualizar el Recicle view en esta Vista
        LineaRecyclerViewAdapter adapter = new LineaRecyclerViewAdapter(lineas);
        RecyclerView lineaRecyclerView = findViewById(R.id.lineaRecyclerView);
        lineaRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        lineaRecyclerView.setAdapter(adapter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }
}