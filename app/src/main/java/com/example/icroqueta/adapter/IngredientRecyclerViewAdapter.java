package com.example.icroqueta.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.icroqueta.MainActivity;
import com.example.icroqueta.R;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.dto.ProductoCarrito;
import com.example.icroqueta.database.entidades.TipoIngrediente;

import java.util.ArrayList;
import java.util.List;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.MyViewHolder> {
    private final List<TipoIngrediente> ingredientes;

    public IngredientRecyclerViewAdapter(List<TipoIngrediente> productos) {
        this.ingredientes = productos;
        setHasStableIds(true);
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.bind(ingredientes.get(position));

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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
        public final View separador;
        public final TextView nombre;
        public final CheckBox checkBox;
        public DBHelper db;
        static String ultimoTipo = " ";
        static final List<String> idIngredientes = new ArrayList<>();
        public final MainActivity home;
        final int idPersona;

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
            SharedPreferences preferences = itemView.getContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            idPersona = preferences.getInt("id", 0);
        }

        /**
         * Este metodo va a ir actualizando a cada elemento nuevo que se le envie
         *
         * @param ingrediente nuestro objeto
         */
        public void bind(final TipoIngrediente ingrediente) {
            db = new DBHelper();
            String nombreTipo = db.oneTipo(itemView.getContext(), ingrediente.getidTipo()).getNombre();

            if (!ultimoTipo.equals(nombreTipo)) {
                tipo.setText(nombreTipo);
                ultimoTipo = nombreTipo;
            } else {
                tipo.setVisibility(View.GONE);
                separador.setVisibility(View.GONE);
            }
            nombre.setText(db.oneIngrediente(itemView.getContext(), ingrediente.getIdIngrediente()).getNombre());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //primero comprobamos si tiene croquetas ese tipo de ingrediente
                        if(db.idProductosIdIngredientesIsEmpty(itemView.getContext(), ingrediente.getIdIngrediente())){
                            Toast.makeText(home, "No disponemos actualmente de croquetas con este ingrediente", Toast.LENGTH_SHORT).show();
                            checkBox.setChecked(false);
                        }else {
                            //añadimos el id del ingrediente a la lista
                            idIngredientes.add(String.valueOf(ingrediente.getIdIngrediente()));
                            List<String> idProducto = db.idProductosIdIngredientes(itemView.getContext(), idIngredientes);
                            try {
                                home.loadMainRecicler(db.allProductosCarritoById(itemView.getContext(), idPersona, idProducto));
                            } catch (IndexOutOfBoundsException e) {
                                List<ProductoCarrito> productos = db.allProductosCarrito(itemView.getContext(), idPersona);
                                home.loadMainRecicler(productos);
                                Toast.makeText(home, "No disponemos actualmente de croquetas con este ingrediente", Toast.LENGTH_SHORT).show();
                                checkBox.setChecked(false);
                            }
                        }
                    } else {
                        idIngredientes.remove(String.valueOf(ingrediente.getIdIngrediente()));
                        if (idIngredientes.isEmpty()) {
                            List<ProductoCarrito> productos = db.allProductosCarrito(itemView.getContext(), idPersona);
                            home.loadMainRecicler(productos);
                        } else {
                            List<String> idProducto = db.idProductosIdIngredientes(itemView.getContext(), idIngredientes);
                            try {
                                home.loadMainRecicler(db.allProductosCarritoById(itemView.getContext(), idPersona, idProducto));
                            } catch (IndexOutOfBoundsException e) {
                                List<ProductoCarrito> productos = db.allProductosCarrito(itemView.getContext(), idPersona);
                                home.loadMainRecicler(productos);
                                Toast.makeText(home, "No disponemos actualmente de croquetas con este ingrediente", Toast.LENGTH_SHORT).show();
                                checkBox.setChecked(false);
                            }
                        }
                    }
                }
            });
        }
    }
}