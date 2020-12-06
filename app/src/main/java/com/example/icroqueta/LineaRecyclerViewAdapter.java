package com.example.icroqueta;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.DTO.ProductoCarrito;
import com.example.icroqueta.database.entidades.Linea;

import java.util.List;

public class LineaRecyclerViewAdapter extends RecyclerView.Adapter<LineaRecyclerViewAdapter.MyViewHolder> {
    private List<Linea> lineas;

    public LineaRecyclerViewAdapter(List<Linea> lineas) {
        this.lineas = lineas;
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
         * Inicializamos todos los parametros que van a ser reutilizados
         *
         * @param v la view de la activity
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
         * Metodo que se va a ir actualizando a cada elemento nuevo que se le envie
         *
         * @param producto nuestro objeto
         */
        public void bind(final Linea producto) {


            Glide.with(itemView.getContext())
                    .load(producto.getImagen())
                    .placeholder(R.drawable.logo)//En el caso de que no pueda cargar la imagen
                    .override(300, 300)
                    .centerCrop()
                    .into(foto);
            nombre.setText(producto.getNombre());
            precio.setText(producto.getPrecioUd() + "€/ud");
            cantidad.setText(String.valueOf(producto.getCantidad()));

            menos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper db = new DBHelper();
                    //Obtiene el valor del TextView
                    String valor = cantidad.getText().toString();
                    //Se convierte  Integer
                    int aux = Integer.parseInt(valor);
                    //Se define el valor de una resta y en el caso de que el valor sea igual a 0, se mantiene
                    if (aux == 1) {
                        cantidad.setText("0");
                        //Si tuviesemos este elemento en el carrito, entonces lo borramos
                        db.deleteCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto());

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
                    //Obtiene el valor del TextView
                    String valor = cantidad.getText().toString();
                    //Se convierte  Integer
                    int aux = Integer.parseInt(valor);
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
                    //Envia id a la actividad ProductActivity
                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    intent.putExtra("ID_PRODUCTO", producto.getIdProducto());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public LineaRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_layout, parent, false);
        return new LineaRecyclerViewAdapter.MyViewHolder(v);
    }



    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va a enlazando los nuevos
     *
     * @param position la posicion actual en la lista
     * @param holder   mi clase de RecicleView
     */
    @Override
    public void onBindViewHolder(LineaRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.bind(lineas.get(position));
    }

    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va a enlazando los nuevos
     */
    @Override
    public int getItemCount() {
        return lineas.size();
    }
}

