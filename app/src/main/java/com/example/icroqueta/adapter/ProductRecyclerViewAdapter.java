package com.example.icroqueta.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.icroqueta.LoginActivity;
import com.example.icroqueta.ProductActivity;
import com.example.icroqueta.R;
import com.example.icroqueta.ShoppingCarActivity;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.dto.ProductoCarrito;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder> {
    private final List<ProductoCarrito> productos;

    public ProductRecyclerViewAdapter(List<ProductoCarrito> productos) {
        this.productos = productos;
        setHasStableIds(true);
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
    public void onBindViewHolder(final  MyViewHolder holder, int position) {
        holder.bind(productos.get(position));
        holder.setIsRecyclable(false);

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
        public final TextView stock;
        public final ImageButton menos;
        public final ImageButton mas;
        public final TextView cantidad;
        public final LinearLayout fila;
        public final ImageView foto;
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
            precio = v.findViewById(R.id.producto_precio_row);
            stock = v.findViewById(R.id.producto_stock_row);
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


            db = new DBHelper();
            //Esto es por si no hay stock, que se marque que no está
            if (producto.getStock() == 0) {
                //Se retira del carrito
                db.deleteCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto());

                //Se pone una imagen predeterminada
                Glide.with(itemView.getContext())
                        .load(producto.getImagen())
                        .placeholder(R.drawable.logo)//En el caso de que no pueda cargar la imagen
                        .override(300, 300)
                        .centerCrop()
                        .into(foto);
                nombre.setText(producto.getNombre());
                nombre.setTextColor(nombre.getResources().getColor(R.color.disableText));
                precio.setText(String.format("%s€/ud", producto.getPrecioUd()));
                precio.setTextColor(precio.getResources().getColor(R.color.disableText));
                // int count = producto.getStock();
                stock.setText(stock.getResources().getQuantityString(R.plurals.disponibilidad, producto.getStock(), producto.getStock()));
                stock.setTextColor(stock.getResources().getColor(R.color.disableText));

                //Quitamos los botones y cantidad
                menos.setVisibility(View.GONE);
                mas.setVisibility(View.GONE);
                cantidad.setText("0");
                cantidad.setVisibility(View.GONE);

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
            } else {
                Glide.with(itemView.getContext())
                        .load(producto.getImagen())
                        .placeholder(R.drawable.logo)//En el caso de que no pueda cargar la imagen
                        .override(300, 300)
                        .centerCrop()
                        .into(foto);

                nombre.setText(producto.getNombre());
                precio.setText(String.format("%s€/ud", producto.getPrecioUd()));
                stock.setText(stock.getResources().getQuantityString(R.plurals.disponibilidad, producto.getStock(), producto.getStock()));
                cantidad.setText(String.valueOf(producto.getCantidad()));
                menos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String valor = cantidad.getText().toString(); //Cogemos el valor del TextView
                        int aux = Integer.parseInt(valor); //Pasamos el valor a integer

                        if (aux == 1) {   //Este if es para que nunca llege a numeros negativos y además se borre de nuestro carro
                            cantidad.setText("0");
                            db.deleteCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto());
                            //Esto es para comprobar que venimos desde Shopping cart para actualizar el precio total
                            if (itemView.getContext() instanceof ShoppingCarActivity) {
                                ShoppingCarActivity a = (ShoppingCarActivity) itemView.getContext();
                                a.actualizarTotal();
                                a.refrescar();
                            }

                        } else if (aux > 1) {
                            cantidad.setText(String.valueOf(aux - 1));
                            //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
                            if (db.notExistCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto())) {
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

                        //comprobamos que solo puede añadir hasta el stock
                        if (aux+1 <= producto.getStock()) {

                            cantidad.setText(String.valueOf(aux + 1));
                            //Si no está añadido al carrito se añade o si no se actualiza con la nueva cantidad
                            if (db.notExistCarritoProducto(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto())) {
                                db.addCarrito(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
                            } else {
                                db.updateCarrito(itemView.getContext(), LoginActivity.usuario.getIdPersona(), producto.getIdProducto(), Integer.parseInt(cantidad.getText().toString()));
                            }

                            //Esto es para comprobar que venimos desde Shopping cart para actualizar el precio total
                            if (itemView.getContext() instanceof ShoppingCarActivity) {
                                ShoppingCarActivity a = (ShoppingCarActivity) itemView.getContext();
                                a.actualizarTotal();
                            }
                        } else {
                            Toast.makeText(v.getContext(), R.string.noPuedeAgregar, Toast.LENGTH_SHORT).show();
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
}

