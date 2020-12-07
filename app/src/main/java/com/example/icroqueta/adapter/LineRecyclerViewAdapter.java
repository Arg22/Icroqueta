package com.example.icroqueta.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.icroqueta.ProductActivity;
import com.example.icroqueta.R;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Linea;
import com.example.icroqueta.database.entidades.Producto;

import java.util.List;

public class LineRecyclerViewAdapter extends RecyclerView.Adapter<LineRecyclerViewAdapter.MyViewHolder> {
    private final List<Linea> lineas;

    public LineRecyclerViewAdapter(List<Linea> lineas) {
        this.lineas = lineas;
    }

    @NonNull
    @Override
    public LineRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Aqui se crea la nueva View
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linea_layout, parent, false);
        return new LineRecyclerViewAdapter.MyViewHolder(v);
    }

    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va  enlazando los nuevos
     *
     * @param position la posicion actual en la lista
     * @param holder   mi clase de RecicleView
     */
    @Override
    public void onBindViewHolder(LineRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.bind(lineas.get(position));
    }

    /**
     * Esta es la cantidad de veces que va a reutilizar
     * el Recicle view con la posición hasta que llegue a esta cantidad
     */
    @Override
    public int getItemCount() {
        return lineas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView nombre;
        public final TextView cantidad;
        public final TextView precio;
        public final ImageView foto;
        public final LinearLayout fila;


        /**
         * Inicializamos en el contructor todos los parametros
         * que van a ser reutilizados
         *
         * @param v es el layout que vamos a actualizar
         */
        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.lineaNombre);
            cantidad = v.findViewById(R.id.lineaCantidad);
            precio = v.findViewById(R.id.lineaPrecio);
            foto = v.findViewById(R.id.lineaImagen);
            fila = v.findViewById(R.id.lineaRow);

        }

        /**
         * Este metodo va a ir actualizando a cada elemento nuevo que se le envie
         *
         * @param linea nuestro objeto
         */
        public void bind(final Linea linea) {
            DBHelper db = new DBHelper();
            Producto producto = db.findProducto(itemView.getContext(), linea.getIdProducto());

            Glide.with(itemView.getContext())
                    .load(producto.getImagen())
                    .placeholder(R.drawable.logo)//En el caso de que no pueda cargar la imagen
                    .override(300, 300)
                    .centerCrop()
                    .into(foto);
            nombre.setText(producto.getNombre());
            precio.setText(String.format("%s€/ud", producto.getPrecioUd()));
            cantidad.setText(String.valueOf(linea.getCantidad()));

            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Si pulsamos en el producto, abrimos la activity del Producto y le enviamos la id
                    // del producto para que nos carge su infomación
                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    intent.putExtra("ID_PRODUCTO", linea.getIdProducto());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

