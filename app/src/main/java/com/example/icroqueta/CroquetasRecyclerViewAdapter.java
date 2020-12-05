package com.example.icroqueta;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.icroqueta.database.entidades.Producto;

import java.util.List;

public class CroquetasRecyclerViewAdapter extends RecyclerView.Adapter<CroquetasRecyclerViewAdapter.MyViewHolder> {
    private List<Producto> productos;

    public CroquetasRecyclerViewAdapter(List<Producto> productos) {
        this.productos = productos;
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
        public void bind(final Producto producto) {


            Glide.with(itemView.getContext())
                    .load(producto.getImagen())
                    .into(foto);
            nombre.setText(producto.getNombre());
            precio.setText(producto.getPrecioUd() + "€/ud");
            menos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Obtiene el valor del TextView
                    String valor = cantidad.getText().toString();
                    //Se convierte  Integer
                    int aux = Integer.parseInt(valor);
                    //Se define el valor de una resta y en el caso de que el valor sea igual a 0, se mantiene
                    if (aux == 0) {
                        cantidad.setText("0");
                    } else {
                        cantidad.setText(String.valueOf(aux - 1));
                    }
                }
            });
            mas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Obtiene el valor del TextView
                    String valor = cantidad.getText().toString();
                    //Se convierte  Integer
                    int aux = Integer.parseInt(valor);
                    cantidad.setText(String.valueOf(aux + 1));
                }
            });
            cantidad.setText("0");
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Envia id a la actividad ProductActivity
                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    intent.putExtra("ID_PRODUCTO", producto.getIdProducto());
                    v.getContext().startActivity(intent);
                    //todo actualizar esta informacion a la base de datos en linea
                }
            });
        }
    }


    @Override
    public CroquetasRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_layout, parent, false);
        return new MyViewHolder(v);
    }

    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va a enlazando los nuevos
     *
     *@param position la posicion actual en la lista
     * @param holder mi clase de RecicleView
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(productos.get(position));

    }

    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va a enlazando los nuevos
     *
     */
    @Override
    public int getItemCount() {
        return productos.size();
    }
}

