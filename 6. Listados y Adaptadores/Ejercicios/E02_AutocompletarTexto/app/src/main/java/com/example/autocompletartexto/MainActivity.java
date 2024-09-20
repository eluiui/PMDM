package com.example.autocompletartexto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView actvNombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.actvNombres = findViewById(R.id.actvNombres);

        // Crear un adaptador con la lista de nombres y establecerlo en el AutoCompleteTextView
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.nombres, android.R.layout.simple_list_item_1);
        this.actvNombres.setAdapter(adaptador);
        // Configurar el número mínimo de caracteres para comenzar a mostrar sugerencias
        this.actvNombres.setThreshold(1);

        // Configurar el listener para manejar los clics en los elementos del AutoCompleteTextView
        actvNombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Obtener el nombre seleccionado y mostrar un mensaje
                String elemento = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, "Nombre: " + elemento, Toast.LENGTH_SHORT).show();
            }
        });
    }
}