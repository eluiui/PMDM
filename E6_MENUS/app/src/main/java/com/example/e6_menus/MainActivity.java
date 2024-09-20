package com.example.e6_menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Menus_acitvity {
    private TextView bienvenido, jugar;
    private static final int RESPUESTA = 1;
    LinearLayout fondoimagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bienvenido = findViewById(R.id.bienvenido);
        jugar = findViewById(R.id.jugar);
        registerForContextMenu(bienvenido);
        registerForContextMenu(jugar);
        fondoimagen = findViewById(R.id.fondoimagen);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            if (v.getId() == R.id.bienvenido) {
                getMenuInflater().inflate(R.menu.menu_contextual, menu);
            } else if (v.getId() == R.id.jugar) {
                getMenuInflater().inflate(R.menu.menu_contextual2, menu);
            }
        }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.colores4:
                fondoimagen.setBackgroundResource(R.drawable.colores);
                return true;
            case R.id.colores6:
                fondoimagen.setBackgroundResource(R.drawable.coloresdos);
                return true;
            case R.id.colores9:
                fondoimagen.setBackgroundResource(R.drawable.colorestres);
                return true;
            case R.id.eliminar:
                fondoimagen.setBackground(null);
                return true;
            case R.id.negro:
                fondoimagen.setBackgroundColor(getResources().getColor(R.color.black));
                return true;

        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA) {
            if (resultCode == 4) {
                boolean resultOk = data.getBooleanExtra("salida", false);
                if (resultOk==true) {
                    finish();
                }
            }
        }
    }


}