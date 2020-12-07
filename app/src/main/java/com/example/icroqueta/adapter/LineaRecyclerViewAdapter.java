package com.example.icroqueta.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.icroqueta.ProductActivity;
import com.example.icroqueta.R;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Linea;
import com.example.icroqueta.database.entidades.Producto;

import java.util.List;

public class LineaRecyclerViewAdapter extends RecyclerView.Adapter<LineaRecyclerViewAdapter.MyViewHolder> {
    private List<Linea> lineas;

    public LineaRecyclerViewAdapter(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView nombre;
        public final TextView cantidad;
        public final TextView precio;
        public ImageView foto;
        public final LinearLayout fila;


        /**
         * Inicializamos todos los parametros que van a ser reutilizados
         *
         * @param v la view de la activity
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
         * Metodo que se va a ir actualizando a cada elemento nuevo que se le envie
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
            precio.setText(producto.getPrecioUd() + "€/ud");
            cantidad.setText(String.valueOf(linea.getCantidad()));

            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Envia id a la actividad ProductActivity
                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    intent.putExtra("ID_PRODUCTO", linea.getIdProducto());
                    v.getContext().startActivity(intent);


                }
            });
        }
    }

    @Override
    public LineaRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linea_pedido_layout, parent, false);
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

