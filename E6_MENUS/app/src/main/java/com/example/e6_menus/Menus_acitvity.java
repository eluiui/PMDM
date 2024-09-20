package com.example.e6_menus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Menus_acitvity extends AppCompatActivity {
    private static final int RESPUESTA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_acitvity);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.pantalla1:
                intent = new Intent(this, Pantalla1.class);
                startActivityForResult(intent, RESPUESTA);
                return true;

            case R.id.pantalla2:
                intent = new Intent(this, Pantalla2.class);
                startActivityForResult(intent, RESPUESTA);
                return true;

            case R.id.pantalla3:
                intent = new Intent(this, Pantalla3.class);
                startActivityForResult(intent, RESPUESTA);
                return true;

            case R.id.pantalla4:
                intent = new Intent(this, Pantalla4.class);
                startActivityForResult(intent, RESPUESTA);
                return true;

            case R.id.fin:
                showDialogWithOptions();
                return true;
            case R.id.acercade:
                showDialog();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Acerca de la app")
                .setMessage("2º DAM IES Teis"+"\nPMDM"+"\nJesús Blanco Miguez")
                .setIcon(R.drawable.colores)
                .show();
    }
    private void showDialogWithOptions() {
        new AlertDialog.Builder(this)
                .setTitle("Fuera")
                .setMessage("¿Estás seguro de que deseas salir de la aplicación?")
                .setIcon(R.drawable.colores)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("salida", true);
                        setResult(4, returnIntent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA) {
            if (resultCode == RESULT_OK) {
                boolean resultOk = data.getBooleanExtra("resultOk", false);
                if (resultOk==true) {
                    finish();
                }
            }
        }
    }

}