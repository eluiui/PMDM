package com.example.planetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView lvPlanetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPlanetas = findViewById(R.id.lvPlanetas);

        //Crear instancia del adaptador personalizado
        AdaptadorPlanetas adaptador = new AdaptadorPlanetas(this, R.layout.lista,
                getResources().getStringArray(R.array.planetas),
                getResources().getStringArray(R.array.informacionPlanetas),
                getResources().obtainTypedArray(R.array.imagenesPlanetas));

        //Asignar el adaptador a la vista
        lvPlanetas.setAdapter(adaptador);

        //Escuchador
        lvPlanetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Planeta: " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}