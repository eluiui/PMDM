package com.example.repasofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
    private LinearLayout ll_spnCiclo;
    private Spinner spn_ciclo, spn_curso;
    private Button btn_guardar;
    private ListView lv_alumnos;
    private EditText et_alumno;
    private ArrayList<Alumno> arrayAlumnos = new ArrayList<>();

    private String curso = "ESO";
    private String ciclo;
    private String nombre;
    private AdaptadorPersonalizado adapter;
    private int selectedLv;
    private boolean primero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finds();

        listenerCurso();

        listenerCiclo();

        createAdapter();

        clickListenerLv();

        registerForContextMenu(lv_alumnos);

    }

    private void createAdapter() {
        adapter = new AdaptadorPersonalizado(this , R.layout.listview_personalizado, arrayAlumnos);

        lv_alumnos.setAdapter(adapter);
    }

    private void clickListenerLv() {
        lv_alumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, arrayAlumnos.get(position).toString(), Toast.LENGTH_SHORT).show();
                selectedLv = position;

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_contextual, menu);

        menu.setHeaderTitle(arrayAlumnos.get(selectedLv).getNombre());

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.ctx_eliminar:

                arrayAlumnos.remove(selectedLv);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Has eliminado al alumno", Toast.LENGTH_SHORT).show();

            case R.id.ctx_salir:

                Toast.makeText(this, "Has salido del Menú Contextual", Toast.LENGTH_SHORT).show();

        }

        return super.onContextItemSelected(item);
    }

    private void listenerCiclo() {
        spn_ciclo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ciclo = spn_ciclo.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void listenerCurso() {
        spn_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spn_curso.getSelectedItem().toString().equals("Ciclo")){

                    ll_spnCiclo.setVisibility(View.VISIBLE);
                    ciclo = "DAM";

                } else {

                    ll_spnCiclo.setVisibility(View.GONE);
                    ciclo = "";

                }

                curso = spn_curso.getSelectedItem().toString();

                if (!primero){

                    primero = true;

                } else {

                    Toast.makeText(MainActivity.this, curso, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void finds() {
        ll_spnCiclo = findViewById(R.id.ll_spnCiclo);
        spn_curso = findViewById(R.id.spn_curso);
        spn_ciclo = findViewById(R.id.spn_ciclo);
        btn_guardar = findViewById(R.id.btn_guardar);
        lv_alumnos = findViewById(R.id.lv_alumnos);
        et_alumno = findViewById(R.id.et_alumno);
    }


    public void onClickBtn(View view) {

        switch (view.getId()){
            case R.id.btn_guardar:

                if (comprobarEdit()){

                    guardarAlumno();

                }

        }

    }

    private void guardarAlumno() {

        nombre = et_alumno.getText().toString();

        arrayAlumnos.add(new Alumno(nombre, curso, ciclo));

        adapter.notifyDataSetChanged();

    }

    private boolean comprobarEdit() {

        boolean correcto = false;

        if (et_alumno.getText().toString().trim().isEmpty()){

            Toast.makeText(this, "El alumno debe de tener un nombre y apellidos", Toast.LENGTH_SHORT).show();

        } else {

            correcto = true;

        }
        return correcto;

    }
}