package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Intent intent=     // intent= new Intent(this, LoginActivity.class);
        switch (item.getItemId()){
            case R.id.myAccount:
                Toast.makeText(getBaseContext(),"micuenta",Toast.LENGTH_LONG).show();
                break;
            case R.id.myOptions:
                Toast.makeText(getBaseContext(),"misOpciones",Toast.LENGTH_LONG).show();
                break;
            case R.id.myHistory:
                Toast.makeText(getBaseContext(),"myHistory",Toast.LENGTH_LONG).show();
                break;
            case R.id.myOrders:
                Toast.makeText(getBaseContext(),"myOrders",Toast.LENGTH_LONG).show();
                break;

        }



       // startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}