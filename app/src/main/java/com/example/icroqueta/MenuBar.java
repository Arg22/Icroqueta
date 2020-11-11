package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*Aquí va lo referente al menú superior para añadirlo a cualquier Activity
*/

public class MenuBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);
    }

    /**
     * Método para mostrar el menú
     *
     * @param menu objeto menu que hemos creado
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);


        /*Aqui hay que poner un if segun el rol
        MenuItem item = menu.findItem(R.id.myDeliver);
        item.setVisible(false);*/

             /*Aqui hay que poner segun carrit

               MenuItem item = menu.findItem(R.id.myEndPurchase);
        item.setVisible(false);*/


        return true;
    }

    /**
     * Método para las acciones de cada pestaña del menú
     *
     * @param item el elemento seleccionado para realizar
     *             en este caso, abrir otra activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;

        switch (item.getItemId()){
            case R.id.myAccount:
                intent= new Intent(this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.myOptions:
                intent= new Intent(this, OptionActivity.class);
                startActivity(intent);
                break;
            case R.id.myHistory:
                intent= new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.myActiveProduct:
                intent= new Intent(this, ActiveProductActivity.class);
                startActivity(intent);
                break;
            case R.id.myDeliver:
                intent= new Intent(this, DeliverActivity.class);
                startActivity(intent);
                break;
            case R.id.myClose:
                intent= new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.myCart:
                intent= new Intent(this, ShoppingCarActivity.class);
                startActivity(intent);
                break;
            case R.id.myEndPurchase: //cambiar
                intent= new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}