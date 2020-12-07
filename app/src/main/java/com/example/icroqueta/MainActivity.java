package com.example.icroqueta;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.icroqueta.adapter.ProductRecyclerViewAdapter;
import com.example.icroqueta.database.DTO.ProductoCarrito;
import com.example.icroqueta.database.DBHelper;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends MenuBar {
    private AppBarConfiguration mAppBarConfiguration;
    long backPressedTime;
    private static final long TIME_TO_CLOSE_APP = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        List<ProductoCarrito> productos=db.allProductosCarrito(this,LoginActivity.usuario.getIdPersona());

        //Para visualizar el Recicle view en esta Vista
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(productos);
        RecyclerView croquetasRecyclerView = findViewById(R.id.croquetasRecyclerView);
        croquetasRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        croquetasRecyclerView.setAdapter(adapter);
    }




    //Al hacer click en el icono sale el menú lateral
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //todo Futuro - poner funcionalidad al menu lateral y que reaccione con la base de datos

    public void onBackPressed() {
        long  time = System.currentTimeMillis();

        if (time - backPressedTime > TIME_TO_CLOSE_APP) {
             backPressedTime = time;
            Toast.makeText(this, "Pulse otra vez para  salir de la aplicacion",Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(this, "¿Quiere salir de la aplicacion?",Toast.LENGTH_SHORT).cancel();
            super.onBackPressed();
        }
    }
}