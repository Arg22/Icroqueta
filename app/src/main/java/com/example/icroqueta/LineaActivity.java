package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.icroqueta.adapter.LineRecyclerViewAdapter;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Carrito;
import com.example.icroqueta.database.entidades.Linea;
import com.example.icroqueta.database.entidades.Producto;

import java.util.List;
import java.util.Objects;

public class LineaActivity extends AppCompatActivity {
    int id_pedido;
    int idPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea);
        cargarIdUsuario();
        //Carga la id del producto pulsado en el Adapter
        Bundle extras = getIntent().getExtras();
        assert extras != null;
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
        List<Carrito> carrito = db.allCarritoPersona(this, idPersona);

        //Recorre todas las lineas y las comprueba con los pedidos de tu carrito por si tuviese el mismo, se actualizara solo la cantidad
        if (carrito.size() == 0) {
            for (Linea n : lineas) {
                //Buscamos por si acaso ese producto ya no tuviese existencias o la cantidad mayor a la que tiene en stock
                Producto aux = db.findProducto(this, n.getIdProducto());
                if (aux.getStock() != 0) {
                    //Si no, se añade al carrito sin problema
                    db.addCarrito(this, idPersona, n.getIdProducto(),Math.min(aux.getStock(), n.getCantidad()));
                    Toast.makeText(this, "Añadido al carro con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ShoppingCarActivity.class);
                    startActivity(intent);
                }
            }
        } else {
            for (Linea n : lineas) {
                if (comprobarCarrito(carrito, lineas)) {
                    for (Carrito c : carrito) {
                        if (n.getIdProducto() == c.getIdProducto()) {
                            Producto aux = db.findProducto(this, n.getIdProducto());
                            //Si superan el stock, solo se añade hasta el tope
                            if (aux.getStock() == 0) {
                                //En el caso de que el producto no tenga stock, no se añaden
                                Toast.makeText(this, "No puede añadir este pedido por falta de stock en alguno de los productos", Toast.LENGTH_LONG).show();
                            } else
                                //En el caso de que si que haya, pero la cantidad que hay en el carro y en la linea supere el stock actual, se pondrá este como cantidad
                                db.updateCarrito(this, idPersona, n.getIdProducto(), Math.min(aux.getStock(), n.getCantidad() + c.getCantidad()));
                        }
                    }
                    Toast.makeText(this, "Añadido al carro con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ShoppingCarActivity.class);
                    startActivity(intent);
                } else {
                    Producto aux = db.findProducto(this, n.getIdProducto());
                    if (aux.getStock() == 0) {
                        //En el caso de que el producto no tenga stock, no se añaden
                        Toast.makeText(this, "No puede añadir este pedido por falta de stock en alguno de los productos", Toast.LENGTH_LONG).show();
                    }else {    //Si superan el stock, solo se añade hasta el tope
                        db.addCarrito(this, idPersona, n.getIdProducto(),Math.min(aux.getStock(), n.getCantidad()));
                        Toast.makeText(this, "Añadido al carro con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, ShoppingCarActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }
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

    /**
     * Método para sacar el id del usuario de las credenciales guardadas
     */
    private void cargarIdUsuario() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        idPersona = preferences.getInt("id", 0);
    }
}