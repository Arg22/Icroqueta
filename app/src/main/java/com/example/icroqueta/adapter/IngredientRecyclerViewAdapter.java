package com.example.icroqueta.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.icroqueta.R;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Ingrediente;

import java.util.List;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.MyViewHolder> {
    private final List<Ingrediente> ingredientes;

    public IngredientRecyclerViewAdapter(List<Ingrediente> productos) {
        this.ingredientes = productos;
    }

    @NonNull
    @Override
    public IngredientRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Aqui se crea la nueva View
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulateral_layout, parent, false);
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
        holder.bind(ingredientes.get(position));
    }

    /**
     * Esta es la cantidad de veces que va a reutilizar
     * el Recicle view con la posición hasta que llegue a esta cantidad
     */
    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView nombre;
        public CheckBox checkBox;

        public DBHelper db;

        /**
         * Inicializamos en el contructor todos los parametros
         * que van a ser reutilizados
         *
         * @param v es el layout que vamos a actualizar
         */
        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.producto_nombre_row);
            checkBox = v.findViewById(R.id.producto_precio_row);
        }

        /**
         * Este metodo va a ir actualizando a cada elemento nuevo que se le envie
         *
         * @param ingrediente nuestro objeto
         */
        public void bind(final Ingrediente ingrediente) {
            db = new DBHelper();

            nombre.setText(ingrediente.getNombre());

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//Todo check acccion

                }
            });
        }
    }
}


