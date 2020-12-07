package com.example.icroqueta.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.icroqueta.LoginActivity;
import com.example.icroqueta.ProductActivity;
import com.example.icroqueta.R;
import com.example.icroqueta.ShoppingCarActivity;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.DTO.ProductoCarrito;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder> {
    private final List<ProductoCarrito> productos;

    public ProductRecyclerViewAdapter(List<ProductoCarrito> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Aqui se crea la nueva View
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_layout, parent, false);
        return new MyViewHolder(v);
    }

    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va  enlazando los nuevos
     *
     * @param position la posicion actual en la lista
     * @param holder   mi clase de RecicleView
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(productos.get(position));
    }

    /**
     * Esta es la cantidad de veces que va a reutilizar
     * el Recicle view con la posición hasta que llegue a esta cantidad
     */
    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView nombre;
        public final TextView precio;
        public final ImageButton menos;
        public final ImageButton mas;
        public final TextView cantidad;
        public final LinearLayout fila;
        public ImageView foto;

        /**
         * Inicializamos en el contructor todos los parametros
         * que van a ser reutilizados
         *
         * @param v es el layout que vamos a actualizar
         */
        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.producto_nombre_row);
            precio = v.findViewById(R.id.producto_precio_row);
            menos = v.findViewById(R.id.btn_menos_row);
            mas = v.findViewById(R.id.btn_mas_row);
            cantidad = v.findViewById(R.id.producto_cantidad_row);
            fila = v.findViewById(R.id.croquetaRow);
            foto = v.findViewById(R.id.croquetaImagen);
        }

        /**
         * Este metodo va a ir actualizando a cada elemento nuevo que se le envie
         *
         * @param producto nuestro objeto
         */
        public void bind(final ProductoCarrito producto) {
            Glide.with(itemView.getContext())
                    .load(producto.getImagen())
                    .placeholder(R.drawable.logo)//En el caso de que no pueda cargar la imagen
                    .override(300, 300)
                    .centerCrop()
                    .into(foto);

            nombre.setText(producto.getNombre());
            precio.setText(String.format("%s€/ud", producto.getPrecioUd()));
            cantidad.setText(String.valueOf(producto.getCantidad()));
            menos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper db = new DBHelper();
                    String valor = cantidad.getText().toString(); //Cogemos el valor del TextView
                    int aux = Integer.parseInt(valor); //Pasamos el valor a integer

                    if (aux == 1) {   //Este if es para que nunca llege a numeros negativos y además se borre de nuestro carro
                        cantidad.setText("0");
                        db.deleteCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto());
                        ShoppingCarActivity a = (ShoppingCarActivity) itemView.getContext();
                        a.refrescar();

                    } else if (aux > 1) {
                        cantidad.setText(String.valueOf(aux - 1));
                        //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
                        if (!db.existCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto())) {
                            db.addCarrito(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
                        } else {
                            db.updateCarrito(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
                        }
                    }

                    //Esto es para comprobar que venimos desde Shopping cart para actualizar el precio total
                    if (itemView.getContext() instanceof ShoppingCarActivity) {
                        ShoppingCarActivity a = (ShoppingCarActivity) itemView.getContext();
                        a.actualizarTotal();
                    }
                }
            });
            mas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper db = new DBHelper();
                    String valor = cantidad.getText().toString(); //Cogemos el valor del TextView
                    int aux = Integer.parseInt(valor); //Se convierte  Integer
                    cantidad.setText(String.valueOf(aux + 1));
                    //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
                    if (!db.existCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto())) {
                        db.addCarrito(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
                    } else {
                        db.updateCarrito(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
                    }

                    //Esto es para comprobar que venimos desde Shopping cart para actualizar el precio total
                    if (itemView.getContext() instanceof ShoppingCarActivity) {
                        ShoppingCarActivity a = (ShoppingCarActivity) itemView.getContext();
                        a.actualizarTotal();
                    }
                }
            });
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Si pulsamos en el producto, abrimos la activity del Producto y le enviamos la id
                    // del producto para que nos carge su infomación
                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    intent.putExtra("ID_PRODUCTO", producto.getIdProducto());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

