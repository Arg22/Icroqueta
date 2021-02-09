package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.icroqueta.adapter.LineRecyclerViewAdapter;
import com.example.icroqueta.database.DBHelper;
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

        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        id_pedido = extras.getInt("ID_PEDIDO");

        //Esto le envia al LineRecyclerViewAdapter todos pedidos activos
        DBHelper db = new DBHelper();
        List<Linea> lineas = db.allLineasProducto(this, id_pedido);

        //Para visualizar el Recicle view en esta Vista
        LineRecyclerViewAdapter adapter = new LineRecyclerViewAdapter(lineas);
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

    /**
     * Método para agregar las lineas de un pedido a nuestro carrito,
     * si ya tenemos un producto añadido, se sumaran las cantidades.
     *
     * @param view nuestra view
     */
    public void agregarPedido(View view) {
        DBHelper db = new DBHelper();
        List<Linea> lineas = db.allLineasProducto(this, id_pedido);
        List<Carrito> carrito = db.allCarritoPersona(this, LoginActivity.usuario.getIdPersona());

        //todo secundario - optimizar este bucle
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
        Toast.makeText(this, "Añadido al carro con éxito", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ShoppingCarActivity.class);
        startActivity(intent);
    }

    /**
     * Método para comprobar si hay algun producto común
     * entre las lineas de un pedido y el carrito del usuario.
     *
     * @param carrito la lista del carrito del usuario
     * @param lineas  la lista de las lineas de un pedido
     * @return true si se cumple la premisa
     */
    public boolean comprobarCarrito(List<Carrito> carrito, List<Linea> lineas) {
        for (Linea n : lineas) {
            for (Carrito c : carrito) {
                if (n.getIdProducto() == c.getIdProducto()) {
                    return true;
                }
            }
        }
        return false;
    }
}