package com.example.icroqueta;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Pedido;

import java.util.List;

public class PedidosRecyclerViewAdapter extends RecyclerView.Adapter<PedidosRecyclerViewAdapter.MyViewHolder> {
    private List<Pedido> pedidos;

    public PedidosRecyclerViewAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView Fecha;
        public final TextView cantidad;
        public final TextView precio;
        public final TextView estado;
        public final Button boton;
        public final LinearLayout fila;

        /**
         * Inicializamos todos los parametros que van a ser reutilizados
         *
         * @param v la view de la activity
         */
        public MyViewHolder(View v) {
            super(v);
            Fecha = v.findViewById(R.id.fechaPedidos);
            cantidad = v.findViewById(R.id.cantidadsPedio);
            precio = v.findViewById(R.id.precioPedido);
            estado = v.findViewById(R.id.estadoPedido);
            fila = v.findViewById(R.id.pedidoRow);
            boton=(Button)v.findViewById(R.id.botonPedido);
        }

        /**
         * Metodo que se va a ir actualizando a cada elemento nuevo que se le envie
         *
         * @param pedidos nuestro objeto
         */
        public void bind(final Pedido pedidos) {
            DBHelper db = new DBHelper();

            //todo incializar boton segun donde estemos

            Fecha.setText(pedidos.getFechaPedido());
            cantidad.setText(String.valueOf(db.allLineasProducto(itemView.getContext(),pedidos.getIdPedido()).size()));
            precio.setText(pedidos.getImporte()+"€");
            estado.setText("Pedido "+pedidos.getEstado());

            //Si venimos desde la vista de Pedidos activos, podremos cancelar el pedido
            if (itemView.getContext() instanceof ActiveProductActivity) {
                estado.setTextColor(itemView.getResources().getColor(R.color.colorPrimaryDark));
                boton.setText("Cancelar pedido");
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBHelper db = new DBHelper();
                        db.updatePedido(itemView.getContext(),pedidos.getIdPedido(),pedidos.getIdPersona(),pedidos.getFechaPedido(),"Cancelado",pedidos.getImporte());
                        Toast.makeText(itemView.getContext(), "Pedido cancelado con éxito", Toast.LENGTH_SHORT).show();
                        ActiveProductActivity a = (ActiveProductActivity) itemView.getContext();
                        a.refrescar();
                    }
                });
            }

            //Si venimos desde el historial, no veremos el boton
            if (itemView.getContext() instanceof HistoryActivity) {
                boton.setVisibility(View.GONE);
            }

            //Si venimos desde el la vista del repartidor podremos dar por entregado el pedido
            if (itemView.getContext() instanceof DeliverActivity) {
                estado.setText("Sin entregar");
                boton.setText("Entregado");
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBHelper db = new DBHelper();
                        db.updatePedido(itemView.getContext(),pedidos.getIdPedido(),pedidos.getIdPersona(),pedidos.getFechaPedido(),"Entregado",pedidos.getImporte());
                        Toast.makeText(itemView.getContext(), "Pedido entregado con éxito", Toast.LENGTH_SHORT).show();
                        DeliverActivity d = (DeliverActivity) itemView.getContext();
                        d.refrescar();
                    }
                });
            }

            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Envia id a la actividad ProductActivity
                    Intent intent = new Intent(v.getContext(), LineaActivity.class);
                    intent.putExtra("ID_PEDIDO", pedidos.getIdPedido());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public PedidosRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pedido_layout, parent, false);
        return new MyViewHolder(v);
    }

    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va a enlazando los nuevos
     *
     * @param position la posicion actual en la lista
     * @param holder   mi clase de RecicleView
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(pedidos.get(position));
    }

    /**
     * Une la Recicle view con la posición hasta que llegue
     * y va a enlazando los nuevos
     */
    @Override
    public int getItemCount() {
        return pedidos.size();
    }
}

