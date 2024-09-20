package com.example.e14_repaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout;
    private Spinner spCursos, spCiclos;
    private Button guardar;
    private ListView lvalumnos;
    private EditText nombres;
    private ArrayList<Alumno> alumnos = new ArrayList<>();
    private Adaptador adaptador;
    private Alumno alumnoselecionado;
    private boolean inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.ciclos);
        spCursos = findViewById(R.id.spcursos);
        spCiclos = findViewById(R.id.spciclos);
        guardar = findViewById(R.id.guardar);
        lvalumnos = findViewById(R.id.lvalumnos);
        nombres = findViewById(R.id.etNombre);

        setupListeners();
        createAdapter();
        registerForContextMenu(lvalumnos);
    }

    private void createAdapter() {
        adaptador = new Adaptador(this, R.layout.adaptador, alumnos);
        lvalumnos.setAdapter(adaptador);
    }

    private void setupListeners() {
        lvalumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (alumnos.get(position).toString().contains("ESO")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Informacion")
                            .setIcon(R.drawable.eso)
                            .setMessage(alumnos.get(position).toString())
                            .show();
                } else{
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Informacion")
                            .setIcon(R.drawable.sombrero)
                            .setMessage(alumnos.get(position).toString())
                            .show();
                }
            }
        });

        spCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String curso = spCursos.getSelectedItem().toString();
                if (position == 2) {
                    layout.setVisibility(View.VISIBLE);
                } else {
                    layout.setVisibility(View.GONE);
                }
                if (inicio == false) {
                    inicio = true;
                } else {
                    Toast.makeText(MainActivity.this, curso, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menus, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        alumnoselecionado = (Alumno) lvalumnos.getAdapter().getItem(info.position);
        menu.setHeaderTitle(alumnoselecionado.getNombre());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eliminar:
                alumnos.remove(alumnoselecionado);
                adaptador.notifyDataSetChanged();
                Toast.makeText(this, "El alumno " + alumnoselecionado.getNombre() + " fue liminado con exito", Toast.LENGTH_SHORT).show();
                break;
            case R.id.salir:
                Toast.makeText(this, "Has salido del menu contextual", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void onclick(View view) {
        if (nombres.getText().toString().isEmpty()) {
            Toast.makeText(this, "No hay nada escrito", Toast.LENGTH_SHORT).show();
        } else {
            guardarAlumno();
            nombres.setText("");
        }
    }

    private void guardarAlumno() {
        String nombre = nombres.getText().toString();
        if (layout.getVisibility() == View.VISIBLE) {
            alumnos.add(new Alumno(nombre, spCursos.getSelectedItem().toString(), spCiclos.getSelectedItem().toString()));
        } else {
            alumnos.add(new Alumno(nombre, spCursos.getSelectedItem().toString(), ""));
        }
        adaptador.notifyDataSetChanged();
    }
}