package com.example.e14_repasobd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout;
    private Spinner spCursos, spCiclos;
    private ListView lvalumnos;
    private EditText nombres;
    private Adaptador adaptador;
    private BD bd;
    private Alumno alumnoselecionado;
    boolean inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    public void inicializar() {
        layout = findViewById(R.id.ciclos);
        spCursos = findViewById(R.id.spcursos);
        spCiclos = findViewById(R.id.spciclos);
        lvalumnos = findViewById(R.id.lvalumnos);
        nombres = findViewById(R.id.etNombre);

        setupListeners();
        bd = new BD(this);
        createAdapter();
        registerForContextMenu(lvalumnos);
    }

    private void createAdapter() {
        adaptador = new Adaptador(this, R.layout.adaptador, bd.getAllAlumnos());
        lvalumnos.setAdapter(adaptador);
    }

    private void setupListeners() {
        lvalumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(adaptador.getItem(position));
            }
        });

        spCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String curso = spCursos.getSelectedItem().toString();
                layout.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
                if (inicio == false) {
                    inicio = true;
                } else {
                    MensajeToast(curso);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void showAlertDialog(Alumno alumno) {
        int icon = alumno.getCurso().contains("ESO") ? R.drawable.eso : R.drawable.sombrero;
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Informacion")
                .setIcon(icon)
                .setMessage(alumno.toString())
                .show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menus, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        alumnoselecionado = adaptador.getItem(info.position);
        menu.setHeaderTitle(alumnoselecionado.getNombre());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eliminar:
                showDialogWithOptions();
                break;
            case R.id.salir:
                MensajeToast("Has salido del menú contextual");
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void showDialogWithOptions() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Estás seguro de que deseas eliminarlo?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteAlumno();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    private void deleteAlumno() {
        bd.deleteAlumno(alumnoselecionado);
        recargar();
        MensajeToast("El alumno " + alumnoselecionado.getNombre() + " fue eliminado con éxito");
    }

    public void onclick(View view) {
        if (nombres.getText().toString().isEmpty()) {
            MensajeToast("No hay nada escrito");
        } else {
            guardarAlumno();
            nombres.setText("");
        }
    }

    private void guardarAlumno() {
        String nombre = nombres.getText().toString();
        String curso = spCursos.getSelectedItem().toString();
        String ciclo = layout.getVisibility() == View.VISIBLE ? spCiclos.getSelectedItem().toString() : "";

        bd.addAlumno(new Alumno(nombre, curso, ciclo));
        recargar();
    }

    private void recargar() {
        adaptador.clear();
        adaptador.addAll(bd.getAllAlumnos());
        adaptador.notifyDataSetChanged();
    }

    private void MensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}