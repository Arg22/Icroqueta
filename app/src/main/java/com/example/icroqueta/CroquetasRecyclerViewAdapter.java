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

public class CroquetasRecyclerViewAdapter extends RecyclerView.Adapter<CroquetasRecyclerViewAdapter.MyViewHolder> {
    private String[] mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView nombre;
        public final TextView precio;
        public final ImageButton menos;
        public final ImageButton mas;
        public final TextView cantidad;
        public final LinearLayout fila;
        public ImageView foto;

        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.producto_nombre_row);
            precio = v.findViewById(R.id.producto_precio_row);
            menos = v.findViewById(R.id.btn_menos_row);
            mas = v.findViewById(R.id.btn_mas_row);
            cantidad = v.findViewById(R.id.producto_cantidad_row);
            fila = v.findViewById(R.id.croquetaRow);
            foto= v.findViewById(R.id.croquetaImagen);
        }

        public void bind() {
            //todo actualizar esta informacion a la base de datos
            foto.setImageResource(R.drawable.logo);
            nombre.setText("patata");
            precio.setText("777777");
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

                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    v.getContext().startActivity(intent);
                    //todo:mandar id croquetas a shoppingCart + cantidad
                }
            });
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CroquetasRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_row_layout, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //todo:aqui iria la cantidad de elementos de la bd
        return 10;
    }
}

