package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.icroqueta.database.DBHelper;

/*Aquí va lo referente al menú superior para añadirlo a cualquier Activity
 */

public class MenuBar extends AppCompatActivity {
int idPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarIdUsuario();
    }

    /**
     * Método para mostrar el menú
     *
     * @param menu objeto menu que hemos creado
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        cargarIdUsuario();
        DBHelper db= new DBHelper();
        //Esto es para que el usuario no vea la pantalla de repartos si no tiene ese rol
        if (db.isRolPersona(this,idPersona) == 0) {
            MenuItem item = menu.findItem(R.id.myMap);
            item.setVisible(false);
        }
        return true;
    }

    /**
     * Método para las acciones de cada pestaña del menú
     *
     * @param item el elemento seleccionado para realizar
     *             en este caso, abrir otra activity
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.myOptions:
                if (!(this instanceof OptionActivity)) {
                    intent = new Intent(this, OptionActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.myHistory:
                if (!(this instanceof HistoryActivity)) {
                    intent = new Intent(this, HistoryActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.myActiveProduct:
                if (!(this instanceof ActiveProductActivity)) {
                    intent = new Intent(this, ActiveProductActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.myMap:
                if (!(this instanceof MapsActivity)) {
                    intent = new Intent(this, MapsActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.myClose:
                intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.myCart:
                if (!(this instanceof ShoppingCarActivity)) {
                    intent = new Intent(this, ShoppingCarActivity.class);
                    startActivity(intent);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método para sacar el id del usuario de las credenciales guardadas
     */
    private void cargarIdUsuario() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        idPersona = preferences.getInt("id", 0);
    }
}