package com.example.proyecto_blancomiguezjesus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Esta clase representa la actividad principal de la aplicación que maneja los menús y las interacciones de usuario
 * esta solo se llama en "Paginalistas"
 */
public class Menus extends AppCompatActivity {
    private static final int RESPUESTA = 1;

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Datos que contienen el estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
    }

    /**
     * Método que crea el menú de opciones en la barra de acción.
     *
     * @param menu El menú en el que se inflarán los elementos.
     * @return true para mostrar el menú, false en caso contrario.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Método que maneja las selecciones de elementos del menú.
     *
     * @param item El elemento del menú que se ha seleccionado.
     * @return true si el evento se ha procesado correctamente, false en caso contrario.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        String nombre = getIntent().getStringExtra("nombreUsuario");
        switch (item.getItemId()) {
            case R.id.mylist:
                intent = new Intent(this, MiLista.class);
                intent.putExtra("nombreUsuario", nombre);
                startActivityForResult(intent, RESPUESTA);
                return true;
            case R.id.fin:
                showDialogWithOptions();
                return true;
            case R.id.about:
                showDialog();
                return true;
            case R.id.asistente:
                intent = new Intent(this, AiChan.class);
                intent.putExtra("nombreUsuario", nombre);
                startActivityForResult(intent, RESPUESTA);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que muestra un diálogo de información sobre la aplicación.
     */
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.about))
                .setMessage(getString(R.string.app_name) + "\nVersion: 1.0.0.1" + "\n" + getString(R.string.desarollo))
                .setIcon(R.drawable.logo)
                .show();
    }

    /**
     * Método que muestra un diálogo de advertencia antes de salir de la aplicación.
     */
    private void showDialogWithOptions() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.salirapptittle))
                .setMessage(getString(R.string.leaveapp))
                .setIcon(R.drawable.warning)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("resultOk", true);
                        setResult(4, returnIntent);
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}