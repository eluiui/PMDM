package com.example.e05_animales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lvAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAnimales = findViewById(R.id.lvAnimales);

        //Crear la instancia del adaptador personalizado
        AdaptadorAnimales adaptador = new AdaptadorAnimales(this,
                R.layout.lista,
                getResources().getStringArray(R.array.animales),
                getResources().getStringArray(R.array.informacionAnimales),
                getResources().obtainTypedArray(R.array.fotosAnimales),
                getResources().obtainTypedArray(R.array.clasificacionAnimal));

        //Asignar el adaptador a la vista
        lvAnimales.setAdapter(adaptador);

        //Listener
        lvAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String animal = (String) adapterView.getItemAtPosition(i);
                String informacionAnimal = ((AdaptadorAnimales) adapterView.getAdapter()).getInformacionAnimal(i);

                Toast.makeText(MainActivity.this, "Animal: " + animal + "\nInformacion: " + informacionAnimal, Toast.LENGTH_SHORT).show();
            }
        });
    }
}