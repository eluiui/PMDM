package com.example.menuscontextuales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AuxiliarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //listener para el menú de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opc_item1:
                Toast.makeText(this, "Pulsada opción 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item2:
                Toast.makeText(this, "Pulsada opción 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item3:
                Toast.makeText(this, "Pulsada opción 3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item4:
                Toast.makeText(this, "Pulsada opción 4", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item5:
                Toast.makeText(this, "Pulsada opción 5", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item6:
                Toast.makeText(this, "Pulsada opción 6", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_subitem1:
                Toast.makeText(this, "Pulsada opción sub 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_subitem2:
                Toast.makeText(this, "Pulsada opción sub 2", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
