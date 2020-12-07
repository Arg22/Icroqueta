package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.DTO.ProductoCarrito;
import com.example.icroqueta.database.entidades.Carrito;
import com.example.icroqueta.database.entidades.Linea;

import java.util.List;
import java.util.Objects;

public class LineaActivity extends AppCompatActivity {
    int id_pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea);
        //Esto le envia al ActiveRecyclerViewAdapter todos pedidos activos

        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        id_pedido = extras.getInt("ID_PEDIDO");

        DBHelper db = new DBHelper();
        List<Linea> lineas = db.allLineasProducto(this, id_pedido);

        //Para visualizar el Recicle view en esta Vista
        LineaRecyclerViewAdapter adapter = new LineaRecyclerViewAdapter(lineas);
        RecyclerView lineaRecyclerView = findViewById(R.id.lineaRecyclerView);
        lineaRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        lineaRecyclerView.setAdapter(adapter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    public void agregarPedido(View view) {
        DBHelper db = new DBHelper();
        List<Linea> lineas = db.allLineasProducto(this, id_pedido);
        List<Carrito> carrito = db.allCarritoPersona(this, LoginActivity.usuario.getIdPersona());

        //todo Futuro - optimizar este bucle
        if (carrito.size() == 0) {
            for (Linea n : lineas) {
                db.addCarrito(this, LoginActivity.usuario.getIdPersona(), n.getIdProducto(), n.getCantidad());
            }
        } else {
            for (Linea n : lineas) {
                if (comprobarCarrito(carrito, lineas)) {
                    for (Carrito c : carrito) {
                        if (n.getIdProducto() == c.getIdProducto()) {
                            db.updateCarrito(this, LoginActivity.usuario.getIdPersona(), n.getIdProducto(), n.getCantidad() + c.getCantidad());
                        }
                    }
                } else {
                    db.addCarrito(this, LoginActivity.usuario.getIdPersona(), n.getIdProducto(), n.getCantidad());
                }
            }
        }
        Intent intent = new Intent(this, ShoppingCarActivity.class);
        startActivity(intent);
    }

    public boolean comprobarCarrito(List<Carrito> carrito, List<Linea> lineas) {
        for(Linea n : lineas) {
            for (Carrito c : carrito) {
                if (n.getIdProducto() == c.getIdProducto()) {
                    return true;
                }
            }
        }
        return false;
    }
}