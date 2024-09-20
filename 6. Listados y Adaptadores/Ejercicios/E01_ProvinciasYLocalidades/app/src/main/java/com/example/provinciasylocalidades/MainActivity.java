package com.example.provinciasylocalidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spProvincias, spLocalidades;
    private String provincia, localidad;
    private String[] arrayLocalidades;
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los Spinners en el layout
        spProvincias = findViewById(R.id.spProvincias);
        spLocalidades = findViewById(R.id.spLocalidades);

        // Configuración del Spinner de Provincias
        spProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Método para cargar las localidades según la provincia seleccionada
                spinnerLocalidades(i);

                // Obtener la provincia seleccionada
                provincia = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
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
    public void spinnerLocalidades(int i) {
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
}
