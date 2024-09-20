package com.example.ejerciciorepaso;

import androidx.annotation.NonNull;
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
    private LinearLayout llCiclos;
    private Spinner spCursos, spCiclos;
    private Button btnGuardar;
    private ListView lvAlumnos;
    private EditText etNombreApellidos;
    private ArrayList<Alumno> arrayAlumnos = new ArrayList<>();
    private String curso = "ESO";
    private String ciclo;
    private String nombre;
    private AdaptadorAlumno adaptador;
    private int selectedLv;
    private boolean primero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        llCiclos = findViewById(R.id.llCiclos);
        spCursos = findViewById(R.id.spCursos);
        spCiclos = findViewById(R.id.spCiclos);
        btnGuardar = findViewById(R.id.btnGuardar);
        lvAlumnos = findViewById(R.id.lvAlumnos);
        etNombreApellidos = findViewById(R.id.etNombreApellidos);

        // Configurar listeners
        escuchadorCursos();
        escuchadorCiclos();

        // Crear adaptador para la lista de alumnos
        createAdapter();

        // Configurar listener para clics en la lista de alumnos
        clickListenerLv();

        // Registrar la lista de alumnos para el menú contextual
        registerForContextMenu(lvAlumnos);
    }


    // Crear el adaptador para la lista de alumnos
    private void createAdapter() {
        adaptador = new AdaptadorAlumno(this , R.layout.lista, arrayAlumnos);
        lvAlumnos.setAdapter(adaptador);
    }


    // Configurar listener para clics en la lista de alumnos
    private void clickListenerLv() {
        lvAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayAlumnos.get(position).toString(), Toast.LENGTH_SHORT).show();
                selectedLv = position;
            }
        });
    }


    // Crear el menú contextual cuando se realiza un long click en un elemento de la lista
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);

        // Obtener la información del alumno seleccionado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;
        Alumno alumnoSeleccionado = arrayAlumnos.get(position);

        // Establecer el título del menú contextual usando el nombre del alumno seleccionado
        menu.setHeaderTitle(alumnoSeleccionado.getNombre());
    }


    // Manejar las acciones del menú contextual
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()){
            // Eliminar el alumno seleccionado de la lista
            case R.id.ctx_eliminar:
                arrayAlumnos.remove(position);
                adaptador.notifyDataSetChanged();
                Toast.makeText(this, "Has eliminado al alumno", Toast.LENGTH_SHORT).show();
                return true;

            // Salir del menu contextual
            case R.id.ctx_salir:
                Toast.makeText(this, "Has salido del Menú Contextual", Toast.LENGTH_SHORT).show();
                return true;

            // Si no se reconoce la opción del menú, se delega al método de la superclase para manejarlo
            default:
                return super.onContextItemSelected(item);
        }
    }


    // Configurar el listener para el spinner de ciclos
    private void escuchadorCiclos() {
        spCiclos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ciclo = spCiclos.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    // Configurar el listener para el spinner de cursos
    private void escuchadorCursos() {
        spCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spCursos.getSelectedItem().toString().equals("Ciclos")){
                    llCiclos.setVisibility(View.VISIBLE);
                    ciclo = "DAM";
                }
                else {
                    llCiclos.setVisibility(View.GONE);
                    ciclo = "";
                }

                curso = spCursos.getSelectedItem().toString();

                if (!primero){
                    primero = true;
                }
                else {
                    Toast.makeText(MainActivity.this, curso, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    // Manejar el clic en el botón de guardar
    public void onClickBtn(View view) {
        switch (view.getId()){
            case R.id.btnGuardar:
                if (comprobarEdit()){
                    guardarAlumno();
                }
                break;
        }
    }


    // Guardar un nuevo alumno en la lista
    private void guardarAlumno() {
        nombre = etNombreApellidos.getText().toString();
        arrayAlumnos.add(new Alumno(nombre, curso, ciclo));
        adaptador.notifyDataSetChanged();
    }


    // Comprobar si se ha ingresado un nombre válido
    private boolean comprobarEdit() {
        boolean nombreValido = false;
        if (etNombreApellidos.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Introduzca nombre y apellidos", Toast.LENGTH_SHORT).show();
        }
        else {
            nombreValido = true;
        }
        return nombreValido;
    }
}
