package com.example.icroqueta;

import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.icroqueta.adapter.ProductRecyclerViewAdapter;
import com.example.icroqueta.database.dto.ProductoCarrito;
import com.example.icroqueta.database.DBHelper;
import com.example.icroqueta.database.entidades.Ingrediente;
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
        List<ProductoCarrito> productos = db.allProductosCarrito(this, LoginActivity.usuario.getIdPersona());

        //Para visualizar el Recicle view en esta Vista
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(productos);
        RecyclerView croquetasRecyclerView = findViewById(R.id.croquetasRecyclerView);
        croquetasRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        croquetasRecyclerView.setAdapter(adapter);

        //Para añadir los checkbox dinámicamente al menu lateral
        configView();
    }


    //Al hacer click en el icono sale el menú lateral
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //todo Cifrar contraseñas
    //todo Stock que deshabilite los botones y ponga
    //todo descuentos
    //todo lupa para buscar
    //todo ordenar por más caro/más comprado
    //todo Futuro - poner funcionalidad al menu lateral y que reaccione con la base de datos
    //todo Traducir strings

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
     * En este metodo cargamos los ingredientes y los checkbox de los ingredientes y que se dividan por tipos de ingredientes
     */
    private void configView() {
        DBHelper db = new DBHelper();
        List<Ingrediente> ingredientes = db.allIngredientes(this);

        //TODO que actualicen los productos
        //todo primero los especiales de sin lactosa, sin gluten y vegetarianos
        //Cargamos el navigation view
        NavigationView navigationView = this.findViewById(R.id.nav_view);
        //Creamos un menu
        Menu menu = navigationView.getMenu();
        //Ponemos un submenu con las distintas categorias
        SubMenu menu1 = menu.addSubMenu("Menu 1");
//Metemos todos los ingredientes de esa categoría
        for (int i = 0; i < 15; i++) {
            menu1.add("Menu 1." + (i + 1));
        }


        SubMenu menu2 = menu.addSubMenu("Menu 2");;
//Metemos todos los ingredientes de esa categoría
        for (int i = 0; i < 7; i++) {
            menu2.add("Menu 2." + (i + 1));
        }

/*

        //Botón para marcar todos si fuese necesario

        //Capturo contenedor de botones
        Menu miContenedor = findViewById(R.id.menulateral_xml); //LinearLayout

        //Indicamos las propiedades que tendrán los elementos que inserte dentro de mi contenedor
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Creamos los elementos en bucle
        for(int i=0; i<numElementos; i++){
            CheckBox chk =  new CheckBox(this);
            //Asignamos las propiedades al elemento
            chk.setLayoutParams(lp);

            //Asignamos texto al elemento
            chk.setText("Checkbox " +String.format("%02d", i+1));

            //Para poder interaccionar con los botones le vamos a asignar un Listener
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        Toast.makeText(MainActivity.this, "Has marcado "+ chk.getText(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Has desmarcado "+ chk.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //Finalmente lo añadimos al contenedor
            miContenedor.addView(chk);
        }*/

        }
}