package com.example.provinciasylocalidades_v2;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView actvProvincias;
    private Spinner spLocalidades;
    private String provincia, localidad;
    private String[] arrayLocalidades;
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actvProvincias = findViewById(R.id.actvProvincias);
        spLocalidades = findViewById(R.id.spLocalidades);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_list_item_1);
        this.actvProvincias.setAdapter(adaptador);
        this.actvProvincias.setThreshold(1);

        actvProvincias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Obtener la provincia seleccionada
                provincia = adapterView.getItemAtPosition(i).toString();

                // Buscar el índice de la provincia seleccionada en el array de provincias
                int indiceProvincia = buscarIndiceProvincia(provincia);

                // Llama al método para cargar las localidades según el índice de la provincia seleccionada
                cargarLocalidades(indiceProvincia);
            }
        });

        // Configuración del Spinner de Localidades
        spLocalidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Obtener la localidad seleccionada
                localidad = adapterView.getItemAtPosition(i).toString();
                // Mostrar un mensaje con la provincia y la localidad seleccionadas
                Toast.makeText(MainActivity.this, "Provincia: " + provincia + "\nLocalidad: " + localidad, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    // Método para cargar las localidades según la provincia seleccionada en el Spinner de Provincias
    public void cargarLocalidades(int i) {
        switch (i) {
            case 0:
                arrayLocalidades = getResources().getStringArray(R.array.localidadesACoruña);
                break;

            case 1:
                arrayLocalidades = getResources().getStringArray(R.array.localidadesLugo);
                break;

            case 2:
                arrayLocalidades = getResources().getStringArray(R.array.localidadesPontevedra);
                break;

            case 3:
                arrayLocalidades = getResources().getStringArray(R.array.localidadesOurense);
                break;
        }
        // Crear un adaptador para las localidades y establecerlo en el Spinner de Localidades
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayLocalidades);
        spLocalidades.setAdapter(adaptador);
    }

    // Método para buscar el índice de la provincia seleccionada en el array de provincias
    private int buscarIndiceProvincia(String provincia) {
        String[] provincias = getResources().getStringArray(R.array.provincias);
        for (int i = 0; i < provincias.length; i++) {
            if (provincias[i].equals(provincia)) {
                return i;
            }
        }
        return -1; // Si no se encuentra la provincia
    }
}