package com.example.menuscontextuales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends AuxiliarActivity {
    private TextView tvHello;
    private ImageView ivAbc;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = findViewById(R.id.tvHello);
        //asociar el menu contextual a la TextView
        registerForContextMenu(tvHello);
        //asociar el menu contextual a la ImageView
        ivAbc = findViewById(R.id.ivAbc);
        registerForContextMenu(ivAbc);
    }

    //inflar el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        if (v.getId() == R.id.tvHello) {
            inflater.inflate(R.menu.menu_contextual, menu);

        } else if (v.getId() == R.id.ivAbc) {
            inflater.inflate(R.menu.menu_contextual_imagen, menu);
        }
    }

    //listener para el menú de contextual
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opc_ctx_item1:
                Toast.makeText(this, "Pulsado opción contextual 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_ctx_item2:
                Toast.makeText(this, "Pulsada opción contextual 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_ctx_item3:
                Toast.makeText(this, "Pulsada opción contextual 3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_img_item1:
                Toast.makeText(this, "Pulsada opción de imagen 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_img_item2:
                Toast.makeText(this, "Pulsada opción de imagen 2", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /*
        //inflar el menú de opciones
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
    */
    //listener del botón
    public void onClickBtn(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);
    }
}