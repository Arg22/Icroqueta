package com.example.icroqueta;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.icroqueta.adapter.OrderRecyclerViewAdapter;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Pedido;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

public class MapsActivity extends MenuBar implements OnMapReadyCallback {
    // Zoom de la api de google-> 1:Mundo, 5:Tierra firme y continente, 10:Ciudad, 15:Calles, 20:Edificios
    private static final float DEFAULT_ZOOM = 15f;
    private MapView mapView;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        mostrarPedidosActivos();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        DBHelper db = new DBHelper();
        List<Pedido> pedidosList = db.allPedidosActivos(this);
        boolean primero = true;
        for (Pedido p : pedidosList) {
            String[] coordenadas = p.getCoordenadas().split(" ");
            LatLng latlng = new LatLng(Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1]));
            googleMap.addMarker(new MarkerOptions().position(latlng).title(p.getPuerta()));
            if (primero) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, DEFAULT_ZOOM));
                primero = false;
            }
        }
    }
    public void mostrarPedidosActivos(){

        //Esto le envia al OrderRecyclerViewAdapter todos pedidos activos
        DBHelper db = new DBHelper();
        List<Pedido> pedidos=db.allPedidosActivos(this);

        //Este aviso sale si no hay pedidos activos
        if(pedidos.size()==0){
            Toast.makeText(this, "No tienes pedidos activos", Toast.LENGTH_SHORT).show();
        }

        //Para visualizar el Recicle view en esta Vista
        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(pedidos);
        RecyclerView activeRecyclerView = findViewById(R.id.deliverRecyclerView);
        activeRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        activeRecyclerView.setAdapter(adapter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Botón home
    }
    @Override
    public boolean onSupportNavigateUp() {//Accion botón
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    /**
     * Método para refrescar la pantalla
     */
    public void refrescar() {
        finish();
        startActivity(getIntent());
    }
}