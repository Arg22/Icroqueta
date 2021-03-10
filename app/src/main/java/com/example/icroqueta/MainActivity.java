package com.example.icroqueta;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.icroqueta.adapter.IngredientRecyclerViewAdapter;
import com.example.icroqueta.adapter.ProductRecyclerViewAdapter;
import com.example.icroqueta.database.dto.ProductoCarrito;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Ingrediente;
import com.example.icroqueta.database.entidades.TipoIngrediente;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends MenuBar {
    private static final long TIME_TO_CLOSE_APP = 5000;
    private AppBarConfiguration mAppBarConfiguration;
    long backPressedTime;
    int idPersona;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarIdUsuario();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();


        //Esto es para que aparezca el icono del menu de la izquierda
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openToolbar, R.string.closeToolbar);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Esto le envia al CroquetasRecyclerViewAdapter todos los productos de la base de datos
        DBHelper db = new DBHelper();
        List<ProductoCarrito> productos = db.allProductosCarrito(this, idPersona);
        loadMainRecicler(productos);


        //Esto le envia al IngredientRecyclerViewAdapter todos los ingredientes de la base de datos
        List<TipoIngrediente> ingredientes = db.allTipoIngredientes(this);

        //Para visualizar el Recicle view en esta Vista
        IngredientRecyclerViewAdapter adapterIng = new IngredientRecyclerViewAdapter(ingredientes);

        RecyclerView ingredientesRecyclerView = findViewById(R.id.ingredienteRecyclerView);
        //Para que no se refresquen a cada item
        ingredientesRecyclerView.setItemViewCacheSize(100);
        ingredientesRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ingredientesRecyclerView.setAdapter(adapterIng);

    }


    //Al hacer click en el icono sale el menú lateral
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Método para salir de la aplicación si se pulsa reiteradamente con el botón back
     */
    public void onBackPressed() {
        long time = System.currentTimeMillis();

        if (time - backPressedTime > TIME_TO_CLOSE_APP) {
            backPressedTime = time;
            Toast.makeText(this, "Pulse otra vez para  salir de la aplicacion", Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(this, "¿Quiere salir de la aplicacion?",Toast.LENGTH_SHORT).cancel();
            super.onBackPressed();
        }
    }

    /**
     * Método para recargar la página principal con los productos
     *
     * @param productos la lista de productos que apareceran en nuestra pantalla
     */
    public void  loadMainRecicler( List<ProductoCarrito> productos){
        //Para visualizar el Recicle view en esta Vista
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(productos);
        RecyclerView croquetasRecyclerView = findViewById(R.id.croquetasRecyclerView);
        croquetasRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        croquetasRecyclerView.setAdapter(adapter);
    }

    /**
     * Método para sacar el id del usuario de las credenciales guardadas
     */
    private void cargarIdUsuario() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        idPersona = preferences.getInt("id", 0);
    }
}


