package com.example.icroqueta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icroqueta.LoginActivity;
import com.example.icroqueta.MainActivity;
import com.example.icroqueta.R;
import com.example.icroqueta.ShoppingCarActivity;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Ingrediente;


import java.util.ArrayList;
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
        public final TextView tipo;
        public View separador;
        public final TextView nombre;
        public CheckBox checkBox;
        public DBHelper db;
        static String ultimoTipo = " ";
        static List<String> idIngredientes = new ArrayList<>();
        public MainActivity home;

        /**
         * Inicializamos en el contructor todos los parametros
         * que van a ser reutilizados
         *
         * @param v es el layout que vamos a actualizar
         */
        public MyViewHolder(View v) {
            super(v);
            tipo = v.findViewById(R.id.titulo_menuLateral);
            nombre = v.findViewById(R.id.nombre_menuLateral);
            checkBox = v.findViewById(R.id.check_menuLateral);
            separador = v.findViewById(R.id.separador_menuLateral);
            home = (MainActivity) itemView.getContext();
        }

        /**
         * Este metodo va a ir actualizando a cada elemento nuevo que se le envie
         *
         * @param ingrediente nuestro objeto
         */
        public void bind(final Ingrediente ingrediente) {
            db = new DBHelper();

            if (!ultimoTipo.equals(ingrediente.getTipo())) {
                tipo.setText(ingrediente.getTipo());
                ultimoTipo = ingrediente.getTipo();

            } else {
                tipo.setVisibility(View.GONE);
                separador.setVisibility(View.GONE);
            }
            nombre.setText(ingrediente.getNombre());


            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //añadimos el id del ingrediente a la lista
                        idIngredientes.add(String.valueOf(ingrediente.getIdIngrediente()));
                        List<String> idProducto=   db.idProductosIdIngredientes(itemView.getContext(),idIngredientes);
                        home.loadMainRecicler(db.allProductosCarritoById(itemView.getContext(), LoginActivity.usuario.getIdPersona(), idProducto));

                    } else {
                        idIngredientes.remove(String.valueOf(ingrediente.getIdIngrediente()));

                    }
                }
            });
        }
    }
}


