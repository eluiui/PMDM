package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello = findViewById(R.id.tvHello);

        //asociar el menu contextual a la TextView
        registerForContextMenu(tvHello);
    }

    //inflar el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    //listener para el menu de opciones
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.opc_ctx_item1:
                Toast.makeText(this, "Seleccionado")
        }
        return super.onContextItemSelected(item);
    }

    //inflar el menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //listener para el menu de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.opc_item1:
                Toast.makeText(this, "Pulsada opcion 1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.opc_item2:
                Toast.makeText(this, "Pulsada opcion 2", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.opc_item3:
                Toast.makeText(this, "Pulsada opcion 3", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.opc_item4:
                Toast.makeText(this, "Pulsada opcion 4", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.opc_item5:
                Toast.makeText(this, "Pulsada opcion 5", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.opc_item6:
                Toast.makeText(this, "Pulsada opcion 6", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.opc_subitem1:
                Toast.makeText(this, "Pulsada sub_item1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.opc_subitem2:
                Toast.makeText(this, "Pulsada sub_item2", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);//valor inicial = false
    }
}